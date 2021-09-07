/*
 * An advance programming project to implement Scotland_Yard game board
 */
package logic;

//import com.sun.corba.se.impl.orbutil.graph.Graph;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;
import org.json.JSONException;

/**
 *
 * @author Rezaei
 */
public class ShortestPathFind {

    ShortestPathFind() {

    }

    List<Station> bestPath(Station start, Station destination, Players[] players) {

        LinkedList<List<Station>> routesQueue = new LinkedList<>();
        Map<Station, Integer> visited = new HashMap<>();
        visited.put(start, 0);
        List<Station> firstRoute = new ArrayList<>();
        firstRoute.add(start);
        routesQueue.add(firstRoute);
        List<Station> bestRoute = new LinkedList<>();

        while (!routesQueue.isEmpty()) {

            List<Station> route = routesQueue.remove();

            Station lastStation = route.get(route.size() - 1);

            if (lastStation.equals(destination)) {

                if (bestRoute.isEmpty()) {
                    bestRoute = route;
                } else {
                    if (route.size() < bestRoute.size() || (route.size() == bestRoute.size()
                            && route.get(1).getIdentifier() < bestRoute.get(1).getIdentifier())) {
                        bestRoute = route;
                    }
                }
            }

            for (Station child : lastStation.getAllNeighbors()) {

                if (!occupied(players, child) && (!visited.containsKey(child) || visited.get(child) == route.size())) {
                    List<Station> newRoute = new ArrayList<>();
                    newRoute.addAll(route);
                    newRoute.add(child);
                    visited.put(child, route.size());
                    routesQueue.add(newRoute);
                }
            }
        }

        return bestRoute;
    }

    private boolean occupied(Players[] players, Station station) {
        boolean occupied = false;

        for (int i = 1; i < players.length && !occupied; i++) {
            if (station.getIdentifier() == players[i].getCurrentStation()) {
                occupied = true;
            }
        }
        return occupied;
    }
}
