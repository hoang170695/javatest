package hoang.tran.test.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingOption {

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    public static final PagingOption DEFAULT = new PagingOption();

    private int pageSize;
    
    private int pageNumber;

    public PagingOption() {
        this.pageSize = 20;
        this.pageNumber = 1;
    }

}
