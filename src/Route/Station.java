package Route;

import java.util.*;

public class Station  {

private final String name;
public static List<Station> stations = new ArrayList<>();
public static Map<String, Map<Station, Integer>> connections = new HashMap<>();

    public Station(String name) {
        this.name = name;
        stations.add(this);
    }


    public void createConnection(){

        Random randConnect = new Random();
        Random randStation = new Random();
        int connectionsNum = randConnect.nextInt(stations.size());

        for (int i = 0; i < connectionsNum; i++) {
            int position1 = randStation.nextInt(stations.size());
            int position2 = randStation.nextInt(stations.size());
            Station station1 = stations.get(position1);
            Station station2 = stations.get(position2);

            if (station1 != station2) {
             //from 1 to second
                Map<Station, Integer> connectionsMap1 = connections.getOrDefault(station1.getName(), new HashMap<>());
                if (!connectionsMap1.containsKey(station2)) {
                    Random randLength1 = new Random();
                    int length1 = randLength1.nextInt(400) + 200;
                    connectionsMap1.put(station2, length1);
                    connections.put(station1.getName(), connectionsMap1);
                }
                //from second to 1
                Map<Station, Integer> connectionsMap2 = connections.getOrDefault(station2.getName(), new HashMap<>());
                if (!connectionsMap2.containsKey(station1)) {
                    Random randLength2 = new Random();
                    int length2 = randLength2.nextInt(400) + 200;
                    connectionsMap2.put(station1, length2);
                    connections.put(station2.getName(), connectionsMap2);
                }
            } else i--;

        }

        }

    public String getName() {
        return name;
    }


}
