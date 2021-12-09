package dongyang.ac.kr.greennaePro.service;

import dongyang.ac.kr.greennaePro.domain.Chat;
import dongyang.ac.kr.greennaePro.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    @Transactional
    public void updateSession(int i){
        Chat chat = new Chat();
        chat.setI(i);
        chatRepository.save(chat);
    }
}
