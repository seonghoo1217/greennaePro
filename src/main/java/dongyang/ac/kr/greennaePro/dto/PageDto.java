package dongyang.ac.kr.greennaePro.dto;

import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
public class PageDto {
    private final int PAGENUM=10;
    private int pageSize;
    private int startPage;
    private int endPage;
    private int curPage;
    private boolean prev,next;

    private long total;

    public PageDto() {
    }

    public PageDto(long total, Pageable pageable) {
        this.total = total;
        this.curPage = pageable.getPageNumber();
        this.pageSize = pageable.getPageSize();

        this.endPage = (int) (Math.ceil((curPage+1) / 10.0)) * 10; // 일단 endPage를 10단위로 세팅, view는 1부터 시작이므로 curPage+1
        this.startPage = this.endPage - (PAGENUM - 1); // 10단위 endPage에서 9를 빼면 시작페이지 구할 수 있음

        int realEnd = (int) (Math.ceil((total * 1.0) / pageSize));

        if (realEnd < this.endPage) { // 페이지가 10단위로 나누어 떨어지지 않을때 real endPage
            this.endPage = realEnd;
        }

        this.prev = (curPage+1) > 1; // view에서는 1부터 시작이므로
        this.next = (curPage+1) < realEnd; // view에서는 1부터 시작이므로
    }
}
