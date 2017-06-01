/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import Characters.AbstractCharacter;
import Characters.Barbarian;
import Characters.Dwarf;
import Characters.Elf;
import Characters.Wizard;
import Controller.Tester;
import GameBoard_Map.Tile;
import Monsters.ChaosWarrior;
import Monsters.Fimir;
import Monsters.Gargoyle;
import Monsters.Goblin;
import Monsters.Mummy;
import Monsters.Orc;
import Monsters.Skeleton;
import Monsters.Zombie;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

/**
 *
 * @author JeremyMcLeod
 */
public class GameBoard {
    private ArrayList<Vertex<Tile>> rowCopy = new ArrayList<Vertex<Tile>>(); 
    private ArrayList<ArrayList<Vertex<Tile>>> board = new ArrayList<ArrayList<Vertex<Tile>>>();
    private ArrayList<Vertex<Tile>> row = new ArrayList<Vertex<Tile>>();
    private Graph gameBoard;
    private int dimension1 = 10;
    private int dimension2 = 10;
    private int width  = 20;
    private int height = 20;
    private JFrame tester = new JFrame("HeroQuest");
    private JPanel sub;
    private JPanel[][] guiTiles;
    private int i;
    private int j;
    private JPanel main = new JPanel();
   
    private GridBagConstraints c = new GridBagConstraints();
    
    public GameBoard()    {  
        
        for(int i = 0; i < 26; i++) {
            row.add(new Vertex<Tile>(new Tile(), i, i));
        }
        int count = 0;
        for(int i = 0; i < 19; i++) {
            for(Vertex<Tile> t: this.row)    {
                this.row.get(count).sety(i);
                count++; 
            }
            ArrayList<Vertex<Tile>> adder = new ArrayList<Vertex<Tile>>();
            for(Vertex v: this.row)    {
                adder.add(v.cloneVertex());
            }
            this.board.add(adder);
            count = 0;
        }
        for(int i = 0; i < 19; i++) {
            for(int j = 0; j < 26; j++) {
                if(j % 2 == 1)  {
                    this.board.get(i).get(j).addEdge(this.board.get(i).get(j-1));
                }
                if(j%2 == 0 && j != 0)    {
                    this.board.get(i).get(j).addEdge(this.board.get(i).get(j-1));
                }
                if(i % 2 == 1)  {

                    this.board.get(i).get(j).addEdge(this.board.get(i-1).get(j));
                }
                if(i % 2 == 0 && i != 0)  {
                    this.board.get(i).get(j).addEdge(this.board.get(i-1).get(j));
                }
                
            }
        }
        for(int i = 0; i < 19; i++) {
            for(int j = 0; j < 26; j++) {
                if(i == 0 || i == 17)   {
                    if (j >= 1 && j <= 11)  {
                        this.board.get(i).get(j).removeEdge(this.board.get(i+1).get(j));
                    }
                    if( j >= 14 && j <=24)  {
                        this.board.get(i).get(j).removeEdge(this.board.get(i+1).get(j));
                    }
                }
                if(i == 3 || i == 8 || i == 9)  {
                     if (j >= 1 && j <= 8)   {
                         this.board.get(i).get(j).removeEdge(this.board.get(i+1).get(j));
                     }
                }
                
                if(i == 4 || i == 8 || i == 9)  {
                    if(j >= 17 && j <= 24)    {
                        this.board.get(i).get(j).removeEdge(this.board.get(i+1).get(j));
                    }
                }
                if(i == 12) {
                   if(j >= 5 && j <= 11) {
                        this.board.get(i).get(j).removeEdge(this.board.get(i+1).get(j));
                   } 
                   if(j >= 14 && j <= 17) {
                        this.board.get(i).get(j).removeEdge(this.board.get(i+1).get(j));
                   }
                }
                if(i == 13) {
                    if(j >= 1 && j <= 4)    {
                        this.board.get(i).get(j).removeEdge(this.board.get(i+1).get(j));
                    }
                    if(j >= 18 && j <=24)    {
                        this.board.get(i).get(j).removeEdge(this.board.get(i+1).get(j));
                    }
                }
                if((i >= 1 && i <= 8) || (i >= 10 && i <= 17))    {
                    if((j == 16 || j == 6) && (i >= 10 && i <=12))    {
                        this.board.get(i).get(j).removeEdge(this.board.get(i).get(j+1));
                    }
                    if(j == 0 || j == 4 || j == 8 || j == 20 || j == 24)    {
                        this.board.get(i).get(j).removeEdge(this.board.get(i).get(j+1));
                    }
                }
                if(j == 11 || j == 13)   {
                    if((i >= 1 && i <= 5) || (i >=13 && i <= 17))    {
                        this.board.get(i).get(j).removeEdge(this.board.get(i).get(j+1));
                    }
                }
                if(j == 17) {
                    if(i >= 13 && i <=17)   {
                        this.board.get(i).get(j).removeEdge(this.board.get(i).get(j+1));
                    }
                }
                if(i == 5)  {
                    if((j >= 9 && j <= 11) || (j >= 14 && j <= 16))  {
                        this.board.get(i).get(j).removeEdge(this.board.get(i+1).get(j));
                    }
                }
                if(j == 16) {
                    if(i >= 1 && i <= 8)    {
                        this.board.get(i).get(j).removeEdge(this.board.get(i).get(j+1));
                    }
                }
                if(i == 6 || i == 11)  {
                    if(j >= 10 && j <= 15)  {
                        this.board.get(i).get(j).removeEdge(this.board.get(i+1).get(j));
                    }
                }
                if(j == 9 || j == 15)   {
                    if (i >= 7 && i <= 11)  {
                        this.board.get(i).get(j).removeEdge(this.board.get(i).get(j+1));
                    }
                }
                //Top Left Tile
                
                if(j == 0 && i == 1)    {
                    this.board.get(i).get(j).removeEdge(this.board.get(i+1).get(j));
                }
                
                //Bottom Tile
                if(j == 14 && i == 18)  {
                    this.board.get(i).get(j).removeEdge(this.board.get(i).get(j-1));
                }
                //Middle Top Tile
                if(j > 10 && j < 14)    {
                    if(i == 5)  {
                        this.board.get(i).get(j).removeEdge(this.board.get(i+1).get(j));
                    }                        
                }
                //Middle Right Tile
                if(j == 17 && i == 9)   {
                        this.board.get(i).get(j).removeEdge(this.board.get(i).get(j-1));
                }
                //Chest in Tomb Room
                if(j == 10 && i == 5)    {
                    this.board.get(i).get(j).removeEdge(this.board.get(i-1).get(j));
                    this.board.get(i).get(j).removeEdge(this.board.get(i).get(j-1));
                    this.board.get(i).get(j).removeEdge(this.board.get(i).get(j+1));
                }
                
            }
        }
        //Readd doors...
        //Top Left Door between 2 Skeletons and Spell Book Room
        this.board.get(3).get(3).addEdge(this.board.get(4).get(3));
        //Top Left Door between 2 skeletons and 2 Zombies and a Mummy
        this.board.get(2).get(4).addEdge(this.board.get(2).get(5));
        //Top Left Door Between 2 Zombies and a Mummy and 2 Skeletons and a Mumm(Tomb Room)
        this.board.get(2).get(8).addEdge(this.board.get(2).get(9));
        //Top Left Door between hallway and Spell Book Room
        this.board.get(9).get(3).addEdge(this.board.get(8).get(3));
        //Top Left Door between hallway and Table Room with 2 goblins
        this.board.get(9).get(7).addEdge(this.board.get(8).get(7));
        //Bottom Left Door between hallway and Torture Rack Room
        this.board.get(11).get(0).addEdge(this.board.get(11).get(1));
        //Bottom Left Door between hallway and Stairway
        this.board.get(18).get(3).addEdge(this.board.get(17).get(3));
        //Bottom left Door between hallway and Study Desk Room with 2 Orcs
        this.board.get(18).get(7).addEdge(this.board.get(17).get(7));
        //Bottom Left Door between Study Desk Room with 2 Orcs and Weapon Rack Room
        this.board.get(15).get(8).addEdge(this.board.get(15).get(9));
        //Bottom Left Door between Weapon Rack Room and Hallway
        this.board.get(13).get(10).addEdge(this.board.get(12).get(10));
        //Bottom Right Door between hallway and Shelves Room with 2 Chaos Warriors
        this.board.get(15).get(13).addEdge(this.board.get(15).get(14));
        //Middle Door between Hallway and Gargoyle Throne Room
        this.board.get(9).get(15).addEdge(this.board.get(9).get(16));
        //Top Left Tomb
        this.board.get(1).get(10).removeEdge(this.board.get(1).get(9));
        this.board.get(2).get(10).removeEdge(this.board.get(2).get(9));
        this.board.get(3).get(10).removeEdge(this.board.get(3).get(9));
        this.board.get(3).get(11).removeEdge(this.board.get(4).get(11));
        this.board.get(3).get(10).removeEdge(this.board.get(4).get(10));
        //Top Left Spell Book
        this.board.get(5).get(2).removeEdge(this.board.get(4).get(2));
        this.board.get(5).get(2).removeEdge(this.board.get(5).get(3));
        this.board.get(6).get(2).removeEdge(this.board.get(6).get(3));
        this.board.get(7).get(2).removeEdge(this.board.get(7).get(3));
        this.board.get(7).get(2).removeEdge(this.board.get(8).get(2));
        this.board.get(5).get(1).removeEdge(this.board.get(4).get(1));
        this.board.get(7).get(1).removeEdge(this.board.get(8).get(1));
        //Top Left Table
        this.board.get(5).get(6).removeEdge(this.board.get(5).get(5));
        this.board.get(5).get(6).removeEdge(this.board.get(4).get(6));
        this.board.get(6).get(6).removeEdge(this.board.get(6).get(5));
        this.board.get(6).get(6).removeEdge(this.board.get(7).get(6));
        this.board.get(5).get(7).removeEdge(this.board.get(4).get(7));
        this.board.get(6).get(7).removeEdge(this.board.get(7).get(7));
        this.board.get(5).get(8).removeEdge(this.board.get(4).get(8));
        this.board.get(6).get(8).removeEdge(this.board.get(7).get(8));
        //Bottom Left Torture Rack
        this.board.get(10).get(3).removeEdge(this.board.get(10).get(2));
        this.board.get(11).get(3).removeEdge(this.board.get(11).get(2));
        this.board.get(12).get(3).removeEdge(this.board.get(12).get(2));
        this.board.get(12).get(3).removeEdge(this.board.get(13).get(3));
        this.board.get(12).get(4).removeEdge(this.board.get(13).get(4));
        //Bottom Left Book Shelf
        this.board.get(13).get(5).removeEdge(this.board.get(14).get(5));
        this.board.get(13).get(6).removeEdge(this.board.get(14).get(6));
        this.board.get(13).get(7).removeEdge(this.board.get(14).get(7));
        this.board.get(13).get(7).removeEdge(this.board.get(13).get(8));
        //Bottom Left Study Desk
        this.board.get(15).get(5).removeEdge(this.board.get(14).get(5));
        this.board.get(15).get(6).removeEdge(this.board.get(14).get(6));
        this.board.get(15).get(6).removeEdge(this.board.get(15).get(7));
        this.board.get(16).get(6).removeEdge(this.board.get(16).get(7));
        this.board.get(17).get(6).removeEdge(this.board.get(17).get(7));
        //Bottom Left Weapon Rack
        this.board.get(15).get(11).removeEdge(this.board.get(14).get(11));
        this.board.get(15).get(11).removeEdge(this.board.get(15).get(10));
        this.board.get(16).get(11).removeEdge(this.board.get(16).get(10));
        this.board.get(17).get(11).removeEdge(this.board.get(17).get(10));
        //Bottom Right Regular Shelf
        this.board.get(13).get(15).removeEdge(this.board.get(13).get(14));
        this.board.get(13).get(15).removeEdge(this.board.get(14).get(15));
        this.board.get(13).get(16).removeEdge(this.board.get(14).get(16));
        this.board.get(13).get(17).removeEdge(this.board.get(14).get(17));
        //Bottom Right Book Shelf
        this.board.get(17).get(15).removeEdge(this.board.get(17).get(14));
        this.board.get(17).get(15).removeEdge(this.board.get(16).get(15));
        this.board.get(17).get(16).removeEdge(this.board.get(16).get(16));
        this.board.get(17).get(17).removeEdge(this.board.get(16).get(17));
        //Bottom Right Chest
        this.board.get(16).get(17).removeEdge(this.board.get(16).get(16));
        this.board.get(16).get(17).removeEdge(this.board.get(15).get(17));
        this.board.get(16).get(17).removeEdge(this.board.get(17).get(17));
        //Middle Table
        this.board.get(9).get(11).removeEdge(this.board.get(9).get(10));
        this.board.get(9).get(11).removeEdge(this.board.get(8).get(11));
        this.board.get(9).get(12).removeEdge(this.board.get(8).get(12));
        this.board.get(9).get(13).removeEdge(this.board.get(8).get(13));
        this.board.get(9).get(13).removeEdge(this.board.get(9).get(14));
        this.board.get(10).get(13).removeEdge(this.board.get(10).get(14));
        this.board.get(10).get(13).removeEdge(this.board.get(11).get(13));
        this.board.get(10).get(12).removeEdge(this.board.get(11).get(12));
        this.board.get(10).get(11).removeEdge(this.board.get(11).get(11));
        this.board.get(10).get(11).removeEdge(this.board.get(10).get(10));
        //Middle Throne
        this.board.get(8).get(10).removeEdge(this.board.get(7).get(10));
        this.board.get(8).get(10).removeEdge(this.board.get(8).get(11));
        this.board.get(8).get(10).removeEdge(this.board.get(9).get(10));
        //Middle Chest
        this.board.get(7).get(11).removeEdge(this.board.get(7).get(10));
        this.board.get(7).get(11).removeEdge(this.board.get(8).get(11));
        this.board.get(7).get(11).removeEdge(this.board.get(7).get(12));
        //Middle Fireplace
        this.board.get(7).get(12).removeEdge(this.board.get(7).get(11));
        this.board.get(7).get(12).removeEdge(this.board.get(8).get(12));
        this.board.get(7).get(13).removeEdge(this.board.get(8).get(13));
        this.board.get(7).get(14).removeEdge(this.board.get(8).get(14));
        this.board.get(7).get(14).removeEdge(this.board.get(7).get(15));
        //Fix 8 4
        
        //System.out.println(this.board);
        this.gameBoard = new Graph(this.board);
        //this.gameBoard.getVertexById(0,0);
        //System.out.println(this.gameBoard.getVertexById(0, 0).getValue());
    }
    public ArrayList<ArrayList<Vertex<Tile>>> getBoard()   {
        return this.board;
    }
    public Graph getBoardGraph()   {
        return this.gameBoard;
    }
    public void setBoardGraph(Graph graph)  {
        this.gameBoard = graph;
    }
    public int getDimension1()  {
        return this.dimension1;
    }
    public int getDimension2()  {
        return this.dimension2;
    }
    /*protected void paintComponent(Graphics g){
        super.paintComponent(g);

        for(int i = 0; i < this.board.size(); i++) {
            for(int j = 0; j < this.row.size(); j++) {
                g.drawRect(this.dimension1, this.dimension2, this.width,this.height);
                //g.setColor(Color.LIGHT_GRAY);
                //g.fillRect(this.dimension1, this.dimension2, this.width, this.height);
                this.dimension1+= 20;
                
            }
            this.dimension1 = 10;
            this.dimension2+=20;
        }
        
    }*/
    public void setupGUI()  {
        tester = new JFrame("Hero Quest");
        Container contentPane = tester.getContentPane();
        tester.setBackground(Color.white);
        tester.setResizable(false);
        tester.setSize((this.dimension1+20)*26+10,(this.dimension2+30)*19);
        tester.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setBackground(Color.black);
        main.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        sub = new JPanel();
        sub.setBackground(Color.black);
        sub.setLayout(new GridLayout(19,26,0,0));//rows,columns,hgap,vgap
        // Create labels and place them on the panel:
        guiTiles = new JPanel[this.board.size()][this.row.size()];
        //One Neighbor
        for(i = 0 ; i < this.board.size() ; i ++ ) {
            for(j = 0; j < this.row.size(); j++) {
                guiTiles[i][j] = new JPanel();
                    /*protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        
                        guiTiles[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                        if(j != 0) {
                            if(!gameBoard.getVertexById(j, i).hasNeighbor(gameBoard.getVertexById(j-1, i)))   {
                                guiTiles[i][j-1].setBorder(new CompoundBorder(
                                    BorderFactory.createMatteBorder(1, 1, 1, 0, Color.GRAY), 
                                    BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK)));
                                guiTiles[i][j].setBorder(new CompoundBorder(
                                    BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY), 
                                    BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK)));

                            }
                        }
                        if(i != 0)  {
                            if(!gameBoard.getVertexById(j, i).hasNeighbor(gameBoard.getVertexById(j, i-1)))   {
                                guiTiles[i-1][j].setBorder(new CompoundBorder(
                                    BorderFactory.createMatteBorder(1, 1, 0, 1, Color.GRAY), 
                                    BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)));
                                guiTiles[i][j].setBorder(new CompoundBorder(
                                    BorderFactory.createMatteBorder(0, 1, 1, 1, Color.GRAY), 
                                    BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK)));

                            }
                        }

                        if(gameBoard.getVertexById(j, i).getValue() instanceof Barbarian)  {
                            JLabel label = new JLabel("B");

                            guiTiles[i][j].add(label);
                            guiTiles[i][j].setBackground(Color.GREEN);
                        }
                        if(gameBoard.getVertexById(j, i).getValue() instanceof Dwarf)  {
                            JLabel label = new JLabel("D");
                            guiTiles[i][j].add(label);
                            guiTiles[i][j].setBackground(Color.GREEN);
                        } 
                        if(gameBoard.getVertexById(j, i).getValue() instanceof Elf)  {
                            JLabel label = new JLabel("E");
                            guiTiles[i][j].add(label);
                            guiTiles[i][j].setBackground(Color.GREEN);
                        } 
                        if(gameBoard.getVertexById(j, i).getValue() instanceof Wizard)  {
                            JLabel label = new JLabel("W");
                            guiTiles[i][j].add(label);
                            guiTiles[i][j].setBackground(Color.GREEN);
                        }
                        if(gameBoard.getVertexById(j, i).getValue() instanceof Goblin)  {
                            JLabel label = new JLabel("G");

                            guiTiles[i][j].add(label);
                            guiTiles[i][j].setBackground(Color.RED);
                        }
                        if(gameBoard.getVertexById(j, i).getValue() instanceof Orc)  {
                            JLabel label = new JLabel("O");

                            guiTiles[i][j].add(label);
                            guiTiles[i][j].setBackground(Color.RED);
                        }
                        if(gameBoard.getVertexById(j, i).getValue() instanceof ChaosWarrior)  {
                            JLabel label = new JLabel("C");

                            guiTiles[i][j].add(label);
                            guiTiles[i][j].setBackground(Color.RED);
                        }
                        if(gameBoard.getVertexById(j, i).getValue() instanceof Fimir)  {
                            JLabel label = new JLabel("F");

                            guiTiles[i][j].add(label);
                            guiTiles[i][j].setBackground(Color.RED);
                        }
                        if(gameBoard.getVertexById(j, i).getValue() instanceof Gargoyle)  {
                            JLabel label = new JLabel("Y");

                            guiTiles[i][j].add(label);
                            guiTiles[i][j].setBackground(Color.RED);
                        }
                        if(gameBoard.getVertexById(j, i).getValue() instanceof Mummy)  {
                            JLabel label = new JLabel("M");

                            guiTiles[i][j].add(label);
                            guiTiles[i][j].setBackground(Color.RED);
                        }
                        if(gameBoard.getVertexById(j, i).getValue() instanceof Skeleton)  {
                            JLabel label = new JLabel("S");

                            guiTiles[i][j].add(label);
                            guiTiles[i][j].setBackground(Color.RED);
                        }
                        if(gameBoard.getVertexById(j, i).getValue() instanceof Zombie)  {
                            JLabel label = new JLabel("Z");

                            guiTiles[i][j].add(label);
                            guiTiles[i][j].setBackground(Color.RED);
                        }
                        //sub.add(guiTiles[i][j]);
                        if(i != 0 && j != 0)    {
                            if(!gameBoard.getVertexById(j, i).hasNeighbor(gameBoard.getVertexById(j-1, i)) && !gameBoard.getVertexById(j, i).hasNeighbor(gameBoard.getVertexById(j, i-1)))  {
                                guiTiles[i][j].setBorder(new CompoundBorder(
                                    BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY), 
                                    BorderFactory.createMatteBorder(2, 2, 0, 0, Color.BLACK)));
                            }
                        }
                        if(i != 0 && j != row.size()-1)    {
                            if(!gameBoard.getVertexById(j, i).hasNeighbor(gameBoard.getVertexById(j+1, i)) && !gameBoard.getVertexById(j, i).hasNeighbor(gameBoard.getVertexById(j, i-1)))  {
                                guiTiles[i][j].setBorder(new CompoundBorder(
                                    BorderFactory.createMatteBorder(0, 1, 1, 0, Color.GRAY), 
                                    BorderFactory.createMatteBorder(2, 0, 0, 2, Color.BLACK)));
                            }
                        }
                        if(j != 0 && i != row.size()-1)    {
                            if(!gameBoard.getVertexById(j, i).hasNeighbor(gameBoard.getVertexById(j-1, i)) && !gameBoard.getVertexById(j, i).hasNeighbor(gameBoard.getVertexById(j, i+1)))  {
                                guiTiles[i][j].setBorder(new CompoundBorder(
                                    BorderFactory.createMatteBorder(1, 0, 0, 1, Color.GRAY), 
                                    BorderFactory.createMatteBorder(0, 2, 2, 0, Color.BLACK)));
                            }
                        }
                        if(i != board.size()-1 && j != row.size()-1)    {
                            if(!gameBoard.getVertexById(j, i).hasNeighbor(gameBoard.getVertexById(j+1, i)) && !gameBoard.getVertexById(j, i).hasNeighbor(gameBoard.getVertexById(j, i+1)))  {
                                guiTiles[i][j].setBorder(new CompoundBorder(
                                    BorderFactory.createMatteBorder(1, 1, 0, 0, Color.GRAY), 
                                    BorderFactory.createMatteBorder(0, 0, 2, 2, Color.BLACK)));
                            }
                        }
                        if(i != 0 && i != board.size()-1)    {
                            if(!gameBoard.getVertexById(j, i).hasNeighbor(gameBoard.getVertexById(j, i+1)) && !gameBoard.getVertexById(j, i).hasNeighbor(gameBoard.getVertexById(j, i-1)))  {
                                guiTiles[i][j].setBorder(new CompoundBorder(
                                    BorderFactory.createMatteBorder(0, 1, 0, 1, Color.GRAY), 
                                    BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK)));
                            }
                        }
                        if(j != 0 && j != row.size()-1)    {
                            if(!gameBoard.getVertexById(j, i).hasNeighbor(gameBoard.getVertexById(j+1, i)) && !gameBoard.getVertexById(j, i).hasNeighbor(gameBoard.getVertexById(j-1, i)))  {
                                guiTiles[i][j].setBorder(new CompoundBorder(
                                    BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY), 
                                    BorderFactory.createMatteBorder(0, 2, 0, 2, Color.BLACK)));
                            }
                        }
                    }
                };*/
                sub.add(guiTiles[i][j]);
                guiTiles[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                if(j != 0) {
                    if(!this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j-1, i)))   {
                        guiTiles[i][j-1].setBorder(new CompoundBorder(
                            BorderFactory.createMatteBorder(1, 1, 1, 0, Color.GRAY), 
                            BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK)));
                        guiTiles[i][j].setBorder(new CompoundBorder(
                            BorderFactory.createMatteBorder(1, 0, 1, 1, Color.GRAY), 
                            BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK)));
                        
                    }
                }
                if(i != 0)  {
                    if(!this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j, i-1)))   {
                        guiTiles[i-1][j].setBorder(new CompoundBorder(
                            BorderFactory.createMatteBorder(1, 1, 0, 1, Color.GRAY), 
                            BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK)));
                        guiTiles[i][j].setBorder(new CompoundBorder(
                            BorderFactory.createMatteBorder(0, 1, 1, 1, Color.GRAY), 
                            BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK)));
                        
                    }
                }
                
                if(this.gameBoard.getVertexById(j, i).getValue() instanceof Barbarian)  {
                    JLabel label = new JLabel("B");
                    
                    guiTiles[i][j].add(label);
                    guiTiles[i][j].setBackground(Color.GREEN);
                }
                if(this.gameBoard.getVertexById(j, i).getValue() instanceof Dwarf)  {
                    JLabel label = new JLabel("D");
                    guiTiles[i][j].add(label);
                    guiTiles[i][j].setBackground(Color.GREEN);
                } 
                if(this.gameBoard.getVertexById(j, i).getValue() instanceof Elf)  {
                    JLabel label = new JLabel("E");
                    guiTiles[i][j].add(label);
                    guiTiles[i][j].setBackground(Color.GREEN);
                } 
                if(this.gameBoard.getVertexById(j, i).getValue() instanceof Wizard)  {
                    JLabel label = new JLabel("W");
                    guiTiles[i][j].add(label);
                    guiTiles[i][j].setBackground(Color.GREEN);
                }
                if(this.gameBoard.getVertexById(j, i).getValue() instanceof Goblin)  {
                    JLabel label = new JLabel("G");
                    
                    guiTiles[i][j].add(label);
                    guiTiles[i][j].setBackground(Color.RED);
                }
                if(this.gameBoard.getVertexById(j, i).getValue() instanceof Orc)  {
                    JLabel label = new JLabel("O");
                    
                    guiTiles[i][j].add(label);
                    guiTiles[i][j].setBackground(Color.RED);
                }
                if(this.gameBoard.getVertexById(j, i).getValue() instanceof ChaosWarrior)  {
                    JLabel label = new JLabel("C");
                    
                    guiTiles[i][j].add(label);
                    guiTiles[i][j].setBackground(Color.RED);
                }
                if(this.gameBoard.getVertexById(j, i).getValue() instanceof Fimir)  {
                    JLabel label = new JLabel("F");
                    
                    guiTiles[i][j].add(label);
                    guiTiles[i][j].setBackground(Color.RED);
                }
                if(this.gameBoard.getVertexById(j, i).getValue() instanceof Gargoyle)  {
                    JLabel label = new JLabel("V");
                    
                    guiTiles[i][j].add(label);
                    guiTiles[i][j].setBackground(Color.RED);
                }
                if(this.gameBoard.getVertexById(j, i).getValue() instanceof Mummy)  {
                    JLabel label = new JLabel("M");
                    
                    guiTiles[i][j].add(label);
                    guiTiles[i][j].setBackground(Color.RED);
                }
                if(this.gameBoard.getVertexById(j, i).getValue() instanceof Skeleton)  {
                    JLabel label = new JLabel("S");
                    
                    guiTiles[i][j].add(label);
                    guiTiles[i][j].setBackground(Color.RED);
                }
                if(this.gameBoard.getVertexById(j, i).getValue() instanceof Zombie)  {
                    JLabel label = new JLabel("Z");
                    
                    guiTiles[i][j].add(label);
                    guiTiles[i][j].setBackground(Color.RED);
                }
                sub.add(guiTiles[i][j]);
            }          
        }
        //Two Neighbors
        for(int i = 0 ; i < this.board.size(); i++ ) {
            for(int j = 0; j < this.row.size(); j++) {  
                if(i != 0 && j != 0)    {
                    if(!this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j-1, i)) && !this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j, i-1)))  {
                        guiTiles[i][j].setBorder(new CompoundBorder(
                            BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY), 
                            BorderFactory.createMatteBorder(2, 2, 0, 0, Color.BLACK)));
                    }
                }
                if(i != 0 && j != this.row.size()-1)    {
                    if(!this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j+1, i)) && !this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j, i-1)))  {
                        guiTiles[i][j].setBorder(new CompoundBorder(
                            BorderFactory.createMatteBorder(0, 1, 1, 0, Color.GRAY), 
                            BorderFactory.createMatteBorder(2, 0, 0, 2, Color.BLACK)));
                    }
                }
                if(j != 0 && i != 18)    {
                    //System.out.println("J is" + j + " and I is " + i);
                    if(!this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j-1, i)) && !this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j, i+1)))  {
                        guiTiles[i][j].setBorder(new CompoundBorder(
                            BorderFactory.createMatteBorder(1, 0, 0, 1, Color.GRAY), 
                            BorderFactory.createMatteBorder(0, 2, 2, 0, Color.BLACK)));
                    }
                }
                if(i != this.board.size()-1 && j != this.row.size()-1)    {
                    if(!this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j+1, i)) && !this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j, i+1)))  {
                        guiTiles[i][j].setBorder(new CompoundBorder(
                            BorderFactory.createMatteBorder(1, 1, 0, 0, Color.GRAY), 
                            BorderFactory.createMatteBorder(0, 0, 2, 2, Color.BLACK)));
                    }
                }
                if(i != 0 && i != this.board.size()-1)    {
                    if(!this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j, i+1)) && !this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j, i-1)))  {
                        guiTiles[i][j].setBorder(new CompoundBorder(
                            BorderFactory.createMatteBorder(0, 1, 0, 1, Color.GRAY), 
                            BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK)));
                    }
                }
                if(j != 0 && j != this.row.size()-1)    {
                    if(!this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j+1, i)) && !this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j-1, i)))  {
                        guiTiles[i][j].setBorder(new CompoundBorder(
                            BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY), 
                            BorderFactory.createMatteBorder(0, 2, 0, 2, Color.BLACK)));
                    }
                }
                
                //Top Left Stone block
                if(j == 0 && i == 1)    {
                    guiTiles[i][j].setBackground(Color.LIGHT_GRAY);                    
                }
                //All of this after this line in the loop is graying out the unused part of the map
                if(j > 17)  {
                    guiTiles[i][j].setBackground(Color.LIGHT_GRAY);
                }
                if(i == 0)  {
                    guiTiles[i][j].setBackground(Color.LIGHT_GRAY);
                }
                if(j == 17 && i < 13)   {
                    guiTiles[i][j].setBackground(Color.LIGHT_GRAY);
                }
                
                if(j >11 && i <6)   {
                    guiTiles[i][j].setBackground(Color.LIGHT_GRAY);
                }
                if(j > 13 && i == 18)   {
                    guiTiles[i][j].setBackground(Color.LIGHT_GRAY);
                }
                if(j > 4 && j < 9)  {
                    if(i > 9 && i < 13) {
                        guiTiles[i][j].setBackground(Color.LIGHT_GRAY);
                    }
                }
                if(j == 10 && i == 5)   {
                    guiTiles[i][j].setBackground(new Color(222,184,134));
                }
                if( j > 9 && j < 12)    {
                    if(i > 0 && i < 4)  {
                        guiTiles[i][j].setBackground(Color.GRAY);
                    }
                }
                if( j > 0 && j < 3) {
                    if(i > 4 && i < 8)  {
                        guiTiles[i][j].setBackground(Color.GRAY);
                    }
                }
                if(j > 5 && j < 9)  {
                    if(i > 4 && i < 7)  {
                        guiTiles[i][j].setBackground(Color.GRAY);
                    }
                }
                if(j > 2 && j < 5)  {
                    if(i > 9 && i < 13) {
                        guiTiles[i][j].setBackground(Color.GRAY);
                    }
                }
                if(j > 4 && j < 7)  {
                    if(i > 14 && i < 18)    {
                        guiTiles[i][j].setBackground(Color.GRAY);
                    }
                }
                if(j == 11) {
                    if(i > 14 && i < 18)    {
                        guiTiles[i][j].setBackground(Color.GRAY);
                    }
                }
                if(j > 14 && j < 18)    {
                    if(i == 17 || i == 13)  {
                        guiTiles[i][j].setBackground(Color.GRAY);
                    }
                }
                if(j == 17) {
                    if(i == 16) {
                        guiTiles[i][j].setBackground(new Color(222,184,134));
                    }
                }
                //Middle Throne
                if(j == 10)	{
                        if(i == 8)	{
                            guiTiles[i][j].setBackground(Color.GRAY);
                        }
                }
                //Middle Chest
                if(j == 11)	{
                        if(i == 7)	{
                            guiTiles[i][j].setBackground(new Color(222,184,134));
                        }
                }
                //Middle Fireplace
                if(j > 11 && j < 15)	{
                        if(i == 7)	{
                            guiTiles[i][j].setBackground(Color.GRAY);
                        }
                }
                //Middle Table
                if(j > 10 && j < 14)	{
                        if(i > 8 && i < 11)	{
                            guiTiles[i][j].setBackground(Color.GRAY);
                        }
                }
                //Bottom Left Book Shelf
                if(j > 4 && j < 8)	{
                        if(i == 13)	{
                            guiTiles[i][j].setBackground(Color.GRAY);
                        }
                }

                
                
                //Middle Right Tile Needs fixing because no cases whee top bottom left checks were made
                
            }
        }
        for(int i = 1 ; i < this.board.size()-1; i++ ) {
            for(int j = 1; j < this.row.size()-1; j++) {
                //Up left right
                if(!this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j+1, i)) && !this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j-1, i)) && !this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j, i-1)) )  {
                    guiTiles[i][j].setBorder(new CompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY), 
                        BorderFactory.createMatteBorder(2, 2, 0, 2, Color.BLACK)));
                }
                //Down Left Right
                if(!this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j+1, i)) && !this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j-1, i)) && !this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j, i+1)))  {
                    guiTiles[i][j].setBorder(new CompoundBorder(
                        BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY), 
                        BorderFactory.createMatteBorder(0, 2, 2, 2, Color.BLACK)));
                }
                //Up right down
                if(!this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j, i+1)) && !this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j, i-1)) && !this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j+1, i)))  {
                    guiTiles[i][j].setBorder(new CompoundBorder(
                        BorderFactory.createMatteBorder(0, 1, 0, 0, Color.GRAY), 
                        BorderFactory.createMatteBorder(2, 0, 2, 2, Color.BLACK)));
                }
                //Up left down
                if(!this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j, i-1)) && !this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j, i+1)) && !this.gameBoard.getVertexById(j, i).hasNeighbor(this.gameBoard.getVertexById(j-1, i)))  {
                    guiTiles[i][j].setBorder(new CompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY), 
                        BorderFactory.createMatteBorder(2, 2, 2, 0, Color.BLACK)));
                        //top left bottom right
                }
            }
        }
        //Top Left Chest
        guiTiles[5][10].setBorder(new CompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY), 
                        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK)));
        //Bottom Right Chest
        guiTiles[16][17].setBorder(new CompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY), 
                        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK)));
        //Middle Chest
        guiTiles[7][11].setBorder(new CompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY), 
                        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK)));
        //Middle Throne
        guiTiles[8][10].setBorder(new CompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY), 
                        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK)));
        //Middle Empty Space
        guiTiles[7][10].setBorder(new CompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY), 
                        BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK)));
        
        
        c.anchor = GridBagConstraints.NORTH; //align top
        c.weighty = 1;     //align top
        c.ipady = 50;      //make this component tall
        c.ipadx = 150;      // Make component Wide
        c.gridwidth = 0;    //Makes buttons appear under you
        
        main.add(sub, c);
        Tester.setupActions(main,c);
        contentPane.add(main);
        tester.pack();
        tester.repaint();
        tester.setVisible(true);
        
    }
    public void update(int x, int y)    {
        if(this.gameBoard.getVertexById(x, y).getValue() instanceof Barbarian)  {
            JLabel label = new JLabel("B");
                    
            guiTiles[y][x].add(label);
            guiTiles[y][x].setBackground(Color.GREEN);
        }
        if(this.gameBoard.getVertexById(x, y).getValue() instanceof Dwarf)  {
            JLabel label = new JLabel("D");
            guiTiles[y][x].add(label);
            guiTiles[y][x].setBackground(Color.GREEN);
        } 
        if(this.gameBoard.getVertexById(x, y).getValue() instanceof Elf)  {
            JLabel label = new JLabel("E");
            guiTiles[y][x].add(label);
            guiTiles[y][x].setBackground(Color.GREEN);
        } 
        if(this.gameBoard.getVertexById(x, y).getValue() instanceof Wizard)  {
            JLabel label = new JLabel("W");
            guiTiles[y][x].add(label);
            guiTiles[y][x].setBackground(Color.GREEN);
        }
        if(this.gameBoard.getVertexById(x, y).getValue() instanceof Goblin)  {
            JLabel label = new JLabel("G");
                    
            guiTiles[y][x].add(label);
            guiTiles[y][x].setBackground(Color.RED);
        }
        if(this.gameBoard.getVertexById(x, y).getValue() instanceof Orc)  {
            JLabel label = new JLabel("O");
                    
            guiTiles[y][x].add(label);
            guiTiles[y][x].setBackground(Color.RED);
        }
        if(this.gameBoard.getVertexById(x, y).getValue() instanceof ChaosWarrior)  {
            JLabel label = new JLabel("C");
                    
            guiTiles[y][x].add(label);
            guiTiles[y][x].setBackground(Color.RED);
        }
        if(this.gameBoard.getVertexById(x, y).getValue() instanceof Fimir)  {
            JLabel label = new JLabel("F");
                    
            guiTiles[y][x].add(label);
            guiTiles[y][x].setBackground(Color.RED);
        }
        if(this.gameBoard.getVertexById(y, x).getValue() instanceof Gargoyle)  {
            JLabel label = new JLabel("V");
                    
            guiTiles[y][x].add(label);
            guiTiles[y][x].setBackground(Color.RED);
        }
        if(this.gameBoard.getVertexById(x, y).getValue() instanceof Mummy)  {
            JLabel label = new JLabel("M");
                    
            guiTiles[y][x].add(label);
            guiTiles[y][x].setBackground(Color.RED);
        }
        if(this.gameBoard.getVertexById(x, y).getValue() instanceof Skeleton)  {
            JLabel label = new JLabel("S");
                    
            guiTiles[y][x].add(label);
            guiTiles[y][x].setBackground(Color.RED);
        }
        if(this.gameBoard.getVertexById(x, y).getValue() instanceof Zombie)  {
            JLabel label = new JLabel("Z");
                    
            guiTiles[y][x].add(label);
            guiTiles[y][x].setBackground(Color.RED);
        }
        if(this.gameBoard.getVertexById(x, y).getValue() instanceof Tile)  {
            guiTiles[y][x].remove(0); 
            guiTiles[y][x].setBackground(Color.WHITE);
            
        }
                
        
        //tester.removeAll();
        //tester.setVisible(false);
        /*Component[] components = sub.getComponents();
        for(Component c: components)    {
            System.out.println(count);
            System.out.println(c);
            count++;
        }*/
        /*
        sub.getComponent(0).setBackground(Color.red);
        tester.add(sub);

        tester.setVisible(true);*/
        //setupGUI();
        
        tester.pack();
        tester.repaint();
    }
    public void updateTile(int x, int y, int x2, int y2)    {
        i = y2;
        j = x2;
        guiTiles[x][y] = new JPanel();
        JPanel newTile = new JPanel();
        JLabel test = new JLabel("B");
        newTile.add(test);
        guiTiles[x2][y2] = newTile;
        guiTiles[x][y].repaint();
        guiTiles[x2][y2].repaint();
    }
    public JFrame getFrame()    {
        return this.tester;
    }
    public JPanel getMain() {
        return this.main;
    }
    public static void main(String[] args)  {
        GameBoard testing = new GameBoard();
        //System.out.println(testing.getBoard());
        AbstractCharacter barbarian = new Barbarian();
        AbstractCharacter dwarf = new Dwarf();
        AbstractCharacter elf = new Elf();
        AbstractCharacter wizard = new Wizard();
        testing.getBoardGraph().getVertexById(2, 15).setValue(barbarian);
        testing.getBoardGraph().getVertexById(1, 15).setValue(dwarf);
        testing.getBoardGraph().getVertexById(2, 14).setValue(elf);
        testing.getBoardGraph().getVertexById(1, 14).setValue(wizard);
        //System.out.println(testing.getBoardGraph().getVertexById(0, 0));
        testing.setupGUI();
        testing.getBoardGraph().getVertexById(2, 15).setValue(new Tile());
        testing.getBoardGraph().getVertexById(2, 16).setValue(barbarian);
        //System.out.println(testing.getFrame().getComponent(0).getComponentAt(1, 15).setBackground(Color.red));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        //testing.update();
        //System.out.println(testing.getBoardGraph().getVertexById(0, 0).getValue());
    }
}
