package Graph;

/**
 * @author Jeremy McLeod Greg Mallon
 * @date   11/18/2014
 * @brief This creates PureBinaryTree to play Col on a graph
*/
import java.lang.*;
import java.io.*;
import java.util.*;
public class Graph<E extends Object>	{
	
    //Used in the constructor to test id's before adding them
  	//to a graph.
    private ArrayList<Vertex<E>> vertexSet; 

  
	/**
	*This is the class Constructor
	*@param value E takes in a value for the constructor
	*@param id int takes in the id of the vertex
	*/
	public Graph(Iterable <Vertex<E>> other){
		vertexSet = new ArrayList<Vertex<E>>();
            try{	
                            for(Vertex<E> element: other){
                                    if(!vertexSet.contains(element.getx()) && !vertexSet.contains(element.gety())){
                                      vertexSet.add(element); 
                                    }
                            }
            }catch(IllegalArgumentException IAE){
                            System.err.println("Duplicate ID.");
                    }
        }		
        public Graph(ArrayList<ArrayList<Vertex<E>>> other){
		vertexSet = new ArrayList<Vertex<E>>();
            try{
                        for(ArrayList<Vertex<E>> sublist: other)    {
                            for(Vertex<E> element: sublist){
                                    if(!vertexSet.contains(element.getx()) && !vertexSet.contains(element.gety())){
                                      vertexSet.add(element); 
                                    }
                            }
                        }
            }catch(IllegalArgumentException IAE){
                            System.err.println("Duplicate ID.");
                    }
        }
  	
  	/**
    *
    *This is the string representation
    *@return s string representation
    *
	*/
	public String toString()	{
    	String s = "";
		int count = 1;
      for(Vertex<E> element: vertexSet){
       		s += "Vertex "+count+": "+element.getValue()+" ,X: "+element.getx()+ " ,Y: "+element.gety()+", Neighbour Id's: " + element.getNeighbors() + "\n\n";
			count++;
      }
      return s;
    }
	
	/**
	*Getter for vertices
	*@return Collection<Vertex<E>> Returns a collection of vertices.
	*
	*/
	public Collection<Vertex<E>> getVertices(){
		return this.vertexSet;
	}
	
	/**
	*This gets a vertex by a specific ID number.
	*@param
	*/
	public Vertex<E> getVertexById(int x, int y){
		for(Vertex<E> element: vertexSet){
			if(element.getx() == x && element.gety() == y){
				return element;
			}
		}
		throw new NoSuchElementException("Ids are not equal");
	}
	
	//private method no javadoc needed
	private int getSize()	{
		return vertexSet.size();
	}
	
	
	/**
	*This tests if two graphs have the same number of
	*vertices, and if those vertices are equal.
	*@param Graph<E> otherGraph 
	*/
	public boolean equals(Graph<E> otherGraph){
		if	(vertexSet.size() != otherGraph.getSize())	{
			return false;
		}
		Graph<E> vertex1 = new Graph<E>(vertexSet);
		for(Vertex<E> element: vertexSet){
			try{
				if(!vertex1.getVertexById(element.getx(), element.gety()).equals(otherGraph.getVertexById(element.getx(), element.gety())))	{
					return false;
				}
			}
			catch (NullPointerException NPE)	{
				return false;
			}
		}
		return true;
	}
	
	
	public static void main(String[] args)	{
		//Create three vertices
                Vertex<String> map1 = new Vertex<String>("Hi", 1, 1);
		Vertex<String> map2 = new Vertex<String>("Hello", 2, 2);
		Vertex<String> map3 = new Vertex<String>("Hi", 3, 3);
		
		
		//create and arraylist to hold created vertices and add those vertices to the arraylist
		ArrayList<Vertex<String>> testUno = new ArrayList<Vertex<String>>();
      	testUno.add(map1);
		testUno.add(map2);
		testUno.add(map3);
		
		//Create an initial graph based on the testUno arraylist
		Graph<String> graph1 = new Graph<String>(testUno);
		System.out.println(graph1);
		
		System.out.println("Testing out getVertexById");
		System.out.println(graph1.getVertexById(2, 2));
 
		//create and arraylist to hold created vertices and add those vertices to the arraylist
		ArrayList<Vertex<String>> test2 = new ArrayList<Vertex<String>>();
                test2.add(map1);
		test2.add(map2);
		test2.add(map3);
		
		//Create an initial graph based on the testUno arraylist
		Graph<String> graph2 = new Graph<String>(test2);
		System.out.println(graph2);
		
		//Create three vertices
                Vertex<String> map7 = new Vertex<String>("Hi", 1, 1);
		Vertex<String> map8 = new Vertex<String>("Hello", 2, 2);
		Vertex<String> map9 = new Vertex<String>("Hi", 9, 9);
		
		//create and arraylist to hold created vertices and add those vertices to the arraylist
		ArrayList<Vertex<String>> test3 = new ArrayList<Vertex<String>>();
                test3.add(map7);
		test3.add(map8);
		test3.add(map9);
		Graph<String> graph3 = new Graph<String>(test3);
		System.out.println("Printing out graph1");
		System.out.println(graph1);
		System.out.println("Printing out graph2");
		System.out.println(graph2);
		System.out.println("Testing equals should be true");
		System.out.println(graph1.equals(graph2));
		System.out.println("Testing equals should throw an exception");
		System.out.println(graph1.equals(graph3));
		
    }
}