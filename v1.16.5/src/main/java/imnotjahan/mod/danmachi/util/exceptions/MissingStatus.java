package imnotjahan.mod.danmachi.util.exceptions;

public class MissingStatus extends RuntimeException
{
    private static final long serialVersionUID = 2256477558314497008L;

    public MissingStatus() {
        super();
    }

    /** @param s Details about the error */
    public MissingStatus(String s) {
        super(s);
    }
}
