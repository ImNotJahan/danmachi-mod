package imnotjahan.mod.danmachi.util.exceptions;

public class MissingStatus extends Exception
{
    private String code;

    public MissingStatus(String code, String message)
    {
        super(message);
        this.setCode(code);
    }

    public MissingStatus(String code, String message, Throwable cause)
    {
        super(message, cause);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
