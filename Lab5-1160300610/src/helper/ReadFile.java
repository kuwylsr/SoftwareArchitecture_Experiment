package helper;


import graph.GraphPoet;
import graph.MovieGraph;
import graph.NetworkTopology;
import graph.SocialNetwork;

public interface ReadFile {

  /**
   * 
   * @return 是否成功解析文件
   */
  public abstract MovieGraph ReadFileToMovieGraph();
  
  /**
   * 
   * @return 是否成功解析文件
   */
  public abstract SocialNetwork ReadFileToSocialNetwork();
  
  /**
   * 
   * @return 是否成功解析文件
   */
  public abstract GraphPoet ReadFileToGraphPoet();
  
  /**
   * 
   * @return 是否成功解析文件
   */
  public abstract NetworkTopology ReadFileToNetworkTopology();
}
