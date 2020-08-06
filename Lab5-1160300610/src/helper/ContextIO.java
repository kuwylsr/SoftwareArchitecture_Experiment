package helper;

import graph.GraphPoet;
import graph.MovieGraph;
import graph.NetworkTopology;
import graph.SocialNetwork;

public class ContextIO {

  
  /**
   * 
   * @param type
   * @param filePath
   * @return
   */
  public boolean WriteInToFile(WriteFile type) {
    return type.WriteGraphToFile();
  }

  /**
   * 
   * @param type
   * @param filePath
   * @return
   */
  public GraphPoet ReadFromFileToGraphPoet(ReadFile type) {
    return type.ReadFileToGraphPoet();
  }
  
  /**
   * 
   * @param type
   * @param filePath
   * @return
   */
  public MovieGraph ReadFromFileToMovieGraph(ReadFile type) {
    return type.ReadFileToMovieGraph();
  }
  

  /**
   * 
   * @param type
   * @param filePath
   * @return
   */
  public NetworkTopology ReadFromFileToNetworkTopology(ReadFile type) {
    return type.ReadFileToNetworkTopology();
  }
  

  /**
   * 
   * @param type
   * @param filePath
   * @return
   */
  public SocialNetwork ReadFromFileToSocialNetwork(ReadFile type) {
    return type.ReadFileToSocialNetwork();
  }
}
