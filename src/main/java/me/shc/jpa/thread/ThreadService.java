package me.shc.jpa.thread;

import java.util.List;
import me.shc.jpa.channel.Channel;

public interface ThreadService {

  List<Thread> selectNotEmptyThreadList(Channel channel);

  Thread insert(Thread thread);

}
