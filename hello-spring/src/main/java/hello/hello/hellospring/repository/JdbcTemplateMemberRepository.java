package hello.hello.hellospring.repository;

import hello.hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// JdbcTemplate: JDBC api의 반복 코드를 대부분 제거해준다
// 단, sql은 직접 작성해야한다. (JPA에선 쿼리마저 없앨 수 있다)

@SuppressWarnings("ALL")
public class JdbcTemplateMemberRepository implements MemberRepository{
    private final JdbcTemplate jdbcTemplate; // 이 라이브러리는 바로 인젝션 받을 수 있는게 아니다.

//    @Autowired // 생성자가 하나만 있을 땐, @Autowired 생략가능
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        // 바로 인젝션 받을 수 없기에, datasource를 주입하여 사용한다.
        jdbcTemplate = new JdbcTemplate(dataSource); // spring이 datasource 자동 injection
    }

    @Override
    public Member save(Member member) {
        // insert query문을 만들어준다(굉장히 편리)
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
        // jdbcTemplate에서 쿼리를 날린다=> 결과를 membeRowMapper에 매핑 => 그걸 result 리스트에 저장
        List<Member> result = jdbcTemplate.query("select * from member where id = ?",memberRowMapper(),id);
        // result를 Optional로 변환해서 리
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 위의 findById에서 id만 name으로 치환
        List<Member> result = jdbcTemplate.query("select * from member where name = ?",memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("slect * from member", memberRowMapper());
    }

    // 객체 생성에 관한 것은, 아래에서 콜백으로 작성된다.
    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
