package data.models;

/**
 *
 * @author Andreas Vikke
 */
public class Redirect {
    private boolean redirect;
    private boolean forward;
    private String location;

    public Redirect(boolean redirect, boolean forward, String location) {
        this.redirect = redirect;
        this.forward = forward;
        this.location = location;
    }

    public boolean isRedirect() {
        return redirect;
    }
    
    public boolean isForward() {
        return forward;
    }

    public String getLocation() {
        return location;
    }

}
