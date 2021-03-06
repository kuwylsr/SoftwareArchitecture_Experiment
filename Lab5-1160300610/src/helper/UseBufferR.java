package helper;

import factory.GraphPoetFactory;
import factory.MovieGraphFactory;
import factory.NetworkTopologyFactory;
import factory.SocialNetworkFactory;
import graph.GraphPoet;
import graph.MovieGraph;
import graph.NetworkTopology;
import graph.SocialNetwork;


public class UseBufferR implements ReadFile{

  
  private final String filePath;
  private long time;

  public UseBufferR(String filePath) {
    this.filePath = filePath;
  }

  /**
   * 获取运行时间
   * @return 运行时间
   */
  public long getTime() {
    return this.time;
  }
  
  @Override
  public MovieGraph ReadFileToMovieGraph() {
    MovieGraphFactory graph = new MovieGraphFactory();
    MovieGraph g=graph.createGraphUseBuffer(filePath);
    this.time = graph.getTime();
    return g;
  }

  @Override
  public SocialNetwork ReadFileToSocialNetwork() {
    SocialNetworkFactory graph = new SocialNetworkFactory();
    SocialNetwork g=graph.createGraphUseBuffer(filePath);
    this.time = graph.getTime();
    return g;
  }

  @Override
  public GraphPoet ReadFileToGraphPoet() {
    GraphPoetFactory graph = new GraphPoetFactory();
    GraphPoet g=graph.createGraphUseBuffer(filePath);
    this.time = graph.getTime();
    return g;
  }

  @Override
  public NetworkTopology ReadFileToNetworkTopology() {
    NetworkTopologyFactory graph = new NetworkTopologyFactory();
    NetworkTopology g=graph.createGraphUseBuffer(filePath);
    this.time = graph.getTime();
    return g;
  }
}
