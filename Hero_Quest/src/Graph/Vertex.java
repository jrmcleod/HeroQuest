package Graph;

/**
 * @author Jeremy McLeod Greg Mallon
 * @date   11/18/2014
 * @brief This creates PureBinaryTree to play Col on a graph
*/
import GameBoard_Map.Tile;
import java.lang.*;
import java.io.*;
import java.util.*;
public class Vertex<E extends Object>	{

	//Instance Data
	private E value;
	private int x;
        private int y;
	private ArrayList<Vertex<E>> neighbors;
        private Vertex<E> other;
	private boolean visited;
	/**
	*This is the class Constructor
	*@param value E takes in a value for the constructor
	*@param id int takes in the id of the vertex
	*/
	public Vertex(E value, int x, int y)	{
		this.value = value;
		this.x = x;
                this.y = y;
		this.neighbors = new ArrayList<Vertex<E>>();
                this.visited = false;
	}
        public Vertex cloneVertex()   {
            Vertex v = new Vertex(value, x, y);
            
            return v;
        }
	
	/**
	*This is the String representation
	*@return Returns a String representation
	*/
	public String toString()	{
		String s = "";
		s+= this.value+", x: "+this.x + ", y: "+this.y + "\n";
		for (int i = 0; i<this.neighbors.size(); i++)	{
			s+= "Neighbor Time" + this.neighbors.get(i).getValue()+", x: "+this.neighbors.get(i).getx() + ", y: " + this.neighbors.get(i).gety() +"\n";
		}
		return s;
	}
	
	/**
	* This is the getter for value
	*@return E value, a single vertex
	*
	*/
	public E getValue(){
		return this.value;
	}
	
	/**
	*This is the getter for id
	*@return int id, a id for a vertex
	*/
	public int getx(){
		return this.x;
	}
        public void setx(int x) {
            this.x = x;
        }
        public int gety(){
		return this.y;
	}
        public void sety(int y)  {
            this.y = y;
        }
        public boolean getVisited() {
            return this.visited;
        }
        public void setVisited(boolean flag)    {
            this.visited = flag;
        }
	
	/**
	*This gets everything connected to a specific vertex
	*@return ArrayList<E> neighbor, returns an arraylist of elements which represent
	*the vertices which are neighbors to a vertex.
	*/
	
	public ArrayList<Vertex<E>> getNeighbors(){
		return this.neighbors;
	}
	
	/**
	*This sets a vertex to a new vertex value
	*@param value E takes in a value for the vertex to be set to
	*@return E returns the new vertex value 
	*/
	public E setValue(E value)	{
		return this.value = value;
	}
	
	/**
	*This checks that the values, ids and numbers of neighbors are the same
	*@param other Vertex<E> takes in a vertex to check against
	*@return true if true, false otherwise
	*/
	public boolean equalsIgnoreIndividualNeighbors(Vertex<E> other)	{
		if(this.getValue().equals(other.getValue()) && this.x == other.getx() && this.y == other.gety() && this.neighbors.size() == other.getNeighbors().size()){
			return true;
		}
		return false;
	}
	
	/**
	* This checks if something has a specific neighbor
	*@param Vertex<E> other
	*@return true if a vertex has another vertex as its neighbor, false otherwise
	*/
	public boolean hasNeighbor(Vertex<E> other)	{
		for(Vertex<E> neighbor : this.neighbors){
			if(neighbor.equalsIgnoreIndividualNeighbors(other))	{
				return true;
			}
		}
		return false;
	}
	
	/**
	*This adds to the neighbors arraylist for a vertex and vice versa
	*@param other Vertex<E> takes in a vertex and adds it to a list of neighbors
	*
	*/
	public void addEdge(Vertex<E> other)	{
		if(!this.hasNeighbor(other)){
			neighbors.add(other);
			other.neighbors.add(this);
		}else{
			
		}
	}
	public void removeEdge(Vertex<E> other) {
            if(this.hasNeighbor(other)) {
                neighbors.remove(other);
                other.neighbors.remove(this);
            }
            else    {
                
            }
        }
	/**
	*
	*First checks using equalsIgnoreIndividualNeighbors then checks that all neighbors are the same.
	*@param other Vertex<E> takes in a vertex and checks equality
	*/
	public boolean equals(Vertex<E> other){
		if(this.equalsIgnoreIndividualNeighbors(other))	{
			for(Vertex<E> element : neighbors)	{
				if	(!element.hasNeighbor(other))	{
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args)	{
		Vertex<String> map1 = new Vertex<String>("Hi",1, 1);
		Vertex<String> map2 = new Vertex<String>("Hi",1, 1);
		Vertex<String> map3 = new Vertex<String>("Hello",2, 2);
		Vertex<String> map4 = new Vertex<String>("Hey",3, 3);
		System.out.println(map1);
		System.out.println(map2);
		
		//Testing out getValue
		System.out.println("Testing out the getValue method in map1, should print out Hi");
		System.out.println(map1.getValue());
		System.out.println("Testing out the getValue method in map2, should print out Hi");
		System.out.println(map2.getValue());
		System.out.println("Testing out the getValue method in map3, should print out Hello");
		System.out.println(map3.getValue());
		System.out.println("Testing out the getValue method in map4, should print out Hey");
		System.out.println(map4.getValue());
		
		//Testing out getValue
		System.out.println("Testing out the getId method in map1, should print out 1");
		System.out.println(map1.getx());
		System.out.println("Testing out the getId method in map2, should print out 1");
		System.out.println(map2.getx());
		System.out.println("Testing out the getId method in map3, should print out 2");
		System.out.println(map3.getx());
		System.out.println("Testing out the getId method in map4, should print out 3");
		System.out.println(map4.getx());
		
		//Testing equalsIgnoreIndividualNeighbors
		System.out.println("Testing equalsIgnoreIndividualNeighbors between map1(Hi, 1) and map2(Hi, 2), should be true");
		System.out.println(map1.equalsIgnoreIndividualNeighbors(map2));
		System.out.println("Testing equalsIgnoreIndividualNeighbors between map1(Hi, 1) and map3(Hello, 3), should be false");
		System.out.println(map1.equalsIgnoreIndividualNeighbors(map3));
		
		map1.addEdge(map2);
		//Testing hasNeighbor, should be true
		System.out.println("Testing hasNeighbor, should be true");
		System.out.println(map1.hasNeighbor(map2));
		//Testing hasNeighbor, should be false
		System.out.println("Testing hasNeighbor, should be false");
		System.out.println(map1.hasNeighbor(map3));
		System.out.println("\n");
		
		//Testing addEdge
		map1.addEdge(map3);
		map1.addEdge(map4);
		System.out.println(map1);
		System.out.println("\n");
		
		//Testing equals
		map2.addEdge(map3);
		map2.addEdge(map4);
		System.out.println(map1 + " "+map1);
		//should be true*/
		System.out.println("Testing equals, should be true");
		System.out.println(map1.equals(map2));
		//should be false
		System.out.println("Testing equals, should be false");
		System.out.println(map1.equals(map3));
		
		
		
	}
	
	
	
	
	
	
	
	
	
	

	
}