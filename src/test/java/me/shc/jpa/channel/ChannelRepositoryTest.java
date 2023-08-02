package me.shc.jpa.channel;

import com.querydsl.core.types.Predicate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional  // 실무에서 사용시 주의해야합니다. (테스트 대상 환경에 영향을 줌)
@Rollback(value = false)
class ChannelRepositoryTest {

  @Autowired
  ChannelRepository channelRepository;


  @Test
  void queryDslTest() {
    // given
    var newChannel = Channel.builder().name("teasun").build();
    channelRepository.save(newChannel);

    Predicate predicate = QChannel.channel
        .name.equalsIgnoreCase("TEASUN");

    // when
    Optional<Channel> optional = channelRepository.findOne(predicate);

    // then
    assert optional.get().getName().equals(newChannel.getName());
  }
}