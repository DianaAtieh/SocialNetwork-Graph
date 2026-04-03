import java.util.ArrayList;

public class SocialNetwork {
    private Graph<String> netWork;
    public SocialNetwork(){
        netWork=new Graph<>();
    }
    public void addUser(String user) throws UserExistException {
        try{
        netWork.addVertex(user); }
        catch(VertexExistException e){
            throw new UserExistException("user already exists");
        }
    }
    public void addFriends(String user1,String user2) throws UserNotFoundException{
        try{
        netWork.addEdge(user1,user2);
        }
        catch(VertexNotExistException e){
            throw new UserNotFoundException("one or both users not found");
        }
    }
    public boolean knows(String user1, String user2) throws UserNotFoundException{
        ArrayList<String> users = netWork.getVertices();

        boolean foundUser1 = false;
        boolean foundUser2 = false;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(user1)) {
                foundUser1 = true;
            }
            if (users.get(i).equals(user2)) {
                foundUser2 = true;
            }
        }

        if (!foundUser1 || !foundUser2) {
            throw new UserNotFoundException("One or both users not found");
        }

        return !netWork.bfs(user1, user2).isEmpty();
    }

}
