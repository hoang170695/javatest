package hoang.tran.test.exception;

public class KeyCannotBeFoundException extends RuntimeException{
    public KeyCannotBeFoundException() {
        super("Key cannot be found");
    }
}
