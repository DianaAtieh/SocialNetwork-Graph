import java.util.*;
public class Graph <E> {

    private ArrayList<ArrayList<E>> data;

    public Graph() {
        this.data = new ArrayList<>();
    }

    public void addVertex(E ver) throws VertexExistException {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get(0).equals(ver))
                throw new VertexExistException("element already exists");
        }
        ArrayList<E> newList=new ArrayList<>();
        newList.add(ver);
        data.add(newList);
    }
    public void addEdge(E ver1,E ver2) throws VertexNotExistException{
       ArrayList<E> listVer1=null;
       ArrayList<E> listVer2=null;
       for(int i=0;i<data.size();i++){
           ArrayList<E> list=data.get(i);
           if(list.get(0).equals(ver1))
               listVer1=list;
           if(list.get(0).equals(ver2))
               listVer2=list;
       }
       if(listVer1==null || listVer2==null)
           throw new VertexNotExistException("one or both vertices not found");
       listVer1.add(ver2);
       listVer2.add(ver1);

    }
    public ArrayList<E> getEdges(E ver) throws VertexNotExistException {
      for(int i=0;i<data.size();i++){
          ArrayList<E> list=data.get(i);
          if(list.get(0).equals(ver)){
              ArrayList<E> res=new ArrayList<>();
              for(int k=1;k< list.size();k++)
                  res.add(list.get(k));
              return res;
          }
      }
      throw new VertexNotExistException("Vertex not found");
    }
    public ArrayList<E> getVertices(){
        ArrayList<E> verList=new ArrayList<>();
        for(int i=0;i<data.size();i++)
            verList.add(data.get(i).get(0));
        return verList;
    }
    public ArrayList<E> bfs(E from, E to) {
        ArrayList<ArrayList<E>> paths = new ArrayList<>();
        ArrayList<E> start = new ArrayList<>();
        start.add(from);
        paths.add(start);

        while (!paths.isEmpty()) {
            ArrayList<E> path = paths.remove(0);
            E last = path.get(path.size() - 1);

            if (last.equals(to)) {
                return path;
            }

            ArrayList<E> neighbors = null;
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).get(0).equals(last)) {
                    neighbors = data.get(i);
                    break;
                }
            }

            if (neighbors != null) {
                for (int i = 1; i < neighbors.size(); i++) {
                    E neighbor = neighbors.get(i);
                    boolean alreadyInPath = false;
                    for (int j = 0; j < path.size(); j++) {
                        if (path.get(j).equals(neighbor)) {
                            alreadyInPath = true;
                            break;
                        }
                    }
                    if (!alreadyInPath) {
                        ArrayList<E> newPath = new ArrayList<>();
                        for (int j = 0; j < path.size(); j++) {
                            newPath.add(path.get(j));
                        }
                        newPath.add(neighbor);
                        paths.add(newPath);
                    }
                }
            }
        }

        return new ArrayList<>();
    }
}