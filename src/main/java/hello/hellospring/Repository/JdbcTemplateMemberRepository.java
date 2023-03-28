package hello.hellospring.Repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    /*
    public JdbcTemplateMemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    JdbcTemplate은 injection 받을 수 없다. DataSource를 injection 받는다
    아래와 같이 쓰면 된다. 스프링에서 권장
     */
//    @Autowired 생성자가 딱 하나만 있으면 스프링 빈으로 등록이 된다. 그러면 @Autowired 생략 가능
    public JdbcTemplateMemberRepository(DataSource dataSource) { // 스프링이 자동으로 DataSource injection 해준다.
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 결과를 rowMapper라는 걸로 매핑해준다.
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        // Optional로 바꿔서 반환
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // List로 결과 반환된다.
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    // 객체 생성 콜백으로 정리 가능
    private RowMapper<Member> memberRowMapper() {
        /*
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {

                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return member;
            }
        }; 아래는 자바8 람다 스타일로 바꾼거
         */
        /*
        rs(resultSet) 결과를 멤버로 객체를 매핑해서 반환한다.
         */
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
