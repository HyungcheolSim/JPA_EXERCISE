package me.shc.jpa.thread;

import java.util.List;
import me.shc.jpa.Mention;
import me.shc.jpa.channel.Channel;
import me.shc.jpa.channel.Channel.Type;
import me.shc.jpa.channel.ChannelRepository;
import me.shc.jpa.user.User;
import me.shc.jpa.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ThreadServiceImplTest {

  @Autowired
  UserRepository userRepository;


  @Autowired
  ChannelRepository channelRepository;

  @Autowired
  ThreadService threadService;

  @Test
  void getMentionedThreadList() {
    // given
    var newUser = User.builder().username("new").password("1").build();
    var savedUser = userRepository.save(newUser);
    Thread newThread3 = threadService.insert(Thread.builder().message("message123").build());
    newThread3.addMention(savedUser);

    Thread newThread4 = threadService.insert(Thread.builder().message("message124").build());
    newThread4.addMention(savedUser);

    // when
    // 모든 채널에서 내가 멘션된 쓰레드 목록 조회 기능
    var mentionedThreads = savedUser.getMentions().stream().map(Mention::getThread).toList();

    // then
    assert mentionedThreads.containsAll(List.of(newThread3, newThread4));
  }

  @Test
  void getNotEmptyThreadList() {
    // given
    var newChannel = Channel.builder().name("c1").type(Type.PUBLIC).build();
    var savedChannel = channelRepository.save(newChannel);
    var newThread = Thread.builder().message("message").build();
    newThread.setChannel(savedChannel);
    threadService.insert(newThread);

    var newThread2 = Thread.builder().message("").build();
    newThread2.setChannel(savedChannel);
    threadService.insert(newThread2);

    // when
    var notEmptyThreads = threadService.selectNotEmptyThreadList(savedChannel);

    // then
    assert !notEmptyThreads.contains(newThread2);
  }
}