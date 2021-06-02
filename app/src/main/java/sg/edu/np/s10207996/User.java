package sg.edu.np.s10207996;

public class User {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String n) {
        this.name = n;
    }

    private String description;
    public String getDescription(){
        return description;
    }
    public void setDescription(String d){
        this.description = d;
    }

    private int id;
    public int getId(){
        return id;
    }
    public void setId(int i){
        this.id = i;
    }

    private boolean followed;
    public boolean getFollowed(){
        return followed;
    }
    public void setFollowed(boolean f){
        this.followed = f;
    }

    public User(String n, String d, int i, boolean f) {
        this.name = n;
        this.description = d;
        this.id = i;
        this.followed = f;
    }

    public boolean Change(){
        if (followed == false){
            followed = true;
        }
        else {
            followed = false;
        }
        return followed;
    }
}

