package org.algosketch.server1;

import org.algosketch.server1.repository.MemberRepository;
import org.algosketch.server1.repository.TodoRepository;
import org.algosketch.server1.service.MemberService;
import org.algosketch.server1.service.TodoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
//    @Bean
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

    private final MemberRepository memberRepository;

    public AppConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    @Bean
//    TodoRepository todoRepository() {
//        return new MemoryTodoRepository();
//    }

    @Bean
    MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    TodoService TodoService() {
//        return new TodoService(todoRepository());
//    }
}
