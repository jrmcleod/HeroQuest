/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Characters.AbstractCharacter;
import Characters.Barbarian;
import Characters.Dwarf;
import Characters.Elf;
import Characters.Wizard;
import GameBoard_Map.Tile;
import Graph.GameBoard;
import Graph.Graph;
import Graph.Vertex;
import Monsters.AbstractMonster;
import Monsters.Goblin;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTextField;
import GameBoard_Map.Tile;
import Monsters.ChaosWarrior;
import Monsters.Fimir;
import Monsters.Gargoyle;
import Monsters.Mummy;
import Monsters.Orc;
import Monsters.Skeleton;
import Monsters.Zombie;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.E;
import java.util.PriorityQueue;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 *
 * @author JeremyMcLeod
 */
public class Tester {
    //private static JPanel main = new JPanel();
    private static JFrame frame;
    private static Barbarian barbarian = new Barbarian();
    private static Dwarf dwarf = new Dwarf();
    private static Elf elf = new Elf();
    private static Wizard wizard = new Wizard();
    private static GameBoard view;
    private static AbstractCharacter hero;
    private AbstractMonster monster;
    private static TurnHandler th;
    private int count;
    private static int x;
    private static int y;
    private static int diceRoll;
    private static int damageValue;
    private static int xMonst;
    private static int yMonst;
    private static int xHero;
    private static int yHero;
    private static ArrayList<AbstractCharacter> heroList = new ArrayList<AbstractCharacter>();
    private static ArrayList<AbstractMonster> monsterList = new ArrayList<AbstractMonster>();
    private static ArrayList<AbstractMonster> targetMonsterList = new ArrayList<AbstractMonster>();
    private static ArrayList<AbstractCharacter> targetHeroList = new ArrayList<AbstractCharacter>();
    private static int indexLoc;
    private static boolean flag;
    private static boolean targetFlag;
    private int loopValue;
    private static int moveHeroValue;
    private static boolean moveFlag = false;
    private static boolean attackFlag = false;
    private static boolean victoryFlag = false;
    private static boolean tortureRoomFlag = false;
    private static int tortureRoomCount = 0;
    private static boolean studyRoomFlag = false;
    private static int studyRoomCount = 0;
    private static boolean weaponRackRoomFlag = false;
    private static int weaponRackRoomCount = 0;
    private static boolean shelvesRoomFlag = false;
    private static int shelvesRoomCount = 0;
    private static boolean middleRoomFlag = false;
    private static int middleRoomCount = 0;
    private static boolean tableRoomFlag = false;
    private static int tableRoomCount = 0;
    private static boolean spellBookRoomFlag = false;
    private static int spellBookRoomCount = 0;
    private static boolean emptySkeletonRoomFlag = false;
    private static int emptySkeletonRoomCount = 0;
    private static boolean emptyZombieRoomFlag = false;
    private static int emptyZombieRoomCount = 0;
    private static boolean tombRoomFlag = false;
    private static int tombRoomCount = 0;
    private static int randomAttackSelect;
    private static int pathwayFindValue;
    private static int newPathwayCreationCount;
    private static boolean monsterMovementFlag;
    private static JLabel moveLabel = new JLabel();
    private static JLabel barbHpLabel = new JLabel();
    private static JLabel dwarfHpLabel = new JLabel();
    private static JLabel elfHpLabel = new JLabel();
    private static JLabel wizardHpLabel = new JLabel();
    private static JLabel barbAttackLabel = new JLabel();
    private static JLabel dwarfAttackLabel = new JLabel();
    private static JLabel elfAttackLabel = new JLabel();
    private static JLabel wizardAttackLabel = new JLabel();
    private static ArrayList<ArrayList<Vertex<Tile>>> pathways = new ArrayList<ArrayList<Vertex<Tile>>>();
    
    public Tester() {
        this.th = new TurnHandler();
    }
    public Tester(GameBoard view, TurnHandler th) {
        this.th = th;
        System.out.println(this.th.getCurrentPlayer());
        this.view = view;
        this.view.setupGUI();
    }
    public static void heroMovement(AbstractCharacter hero, GameBoard board)   {
        Tester.diceRoll = 0;
        for(int i = 0; i < 19; i++)    {
            for(int j = 0; j < 26; j++)    {
                if(board.getBoardGraph().getVertexById(j, i).getValue() == hero)    {
                    //System.out.println(board.getBoardGraph().getVertexById(j, i));
                    Tester.x = j;
                    Tester.y = i;
                }
            }
        }              
        
        hero.rollMovementDice();
        
        Tester.diceRoll += hero.getMovementDice().get(0);
        Tester.diceRoll += hero.getMovementDice().get(1);
        System.out.println("You rolled a " + Tester.diceRoll);
        frame = new JFrame("Movement KeyPad");
        frame.setResizable(false);
        frame.setSize(100,100);
        Container contentPane = frame.getContentPane();
        JButton up = new JButton("Up");
        up.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Up!");
                    try {
                        if(y == 0) {
                            System.out.println("Out of bounds");
                        } 
                        else if(diceRoll != 0)  {
                            if(board.getBoardGraph().getVertexById(x, y).hasNeighbor(board.getBoardGraph().getVertexById(x, y-1)) && board.getBoardGraph().getVertexById(x, y-1).getValue() instanceof Tile)  {
                                board.getBoardGraph().getVertexById(x, y-1).setValue(hero);
                                board.getBoardGraph().getVertexById(x, y).setValue(new Tile());
                                
                                
                                
                                
                                System.out.println("The Barbar is now mapped to " + heroList.get(moveHeroValue).getXLoc() + " " + heroList.get(moveHeroValue).getYLoc());
                                diceRoll--;
                                moveLabel.setText("Movement Points: " + diceRoll);
                                board.update(x,y);
                                board.update(x, y-1);
                                y = y - 1;
                                heroList.get(moveHeroValue).setYLoc(y);
                                //System.out.println(view.getBoardGraph().getVertexById(x,y));
                                checkForMonsterActivations();
                                
                            }
                            else    {
                                System.out.println("You cannot move through walls!");
                            }
                        }
                        if(diceRoll == 0)   {
                           frame.setVisible(false); 
                        }
                        //System.out.println(diceRoll);
                    }
                    catch(IndexOutOfBoundsException ex)   {
                        
                    }
            }
        });
                
        JButton down = new JButton("Down");
        down.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Down!");
                    try {
                        if(y == 18) {
                            System.out.println("Out of bounds");
                        }
                        else if(diceRoll != 0 || y != 18)   {
                            
                            if(board.getBoardGraph().getVertexById(x, y).hasNeighbor(board.getBoardGraph().getVertexById(x, y+1)) && board.getBoardGraph().getVertexById(x, y+1).getValue() instanceof Tile)  {
                                board.getBoardGraph().getVertexById(x, y+1).setValue(hero);
                                board.getBoardGraph().getVertexById(x, y).setValue(new Tile());
                                diceRoll--;
                                moveLabel.setText("Movement Points: " + diceRoll);
                                board.update(x,y);
                                board.update(x, y+1);
                                y = y + 1;
                                System.out.println(heroList.get(moveHeroValue).toString());
                                heroList.get(moveHeroValue).setYLoc(y);
                                System.out.println("The hero is now mapped to " + heroList.get(moveHeroValue).getXLoc() + " " + heroList.get(moveHeroValue).getYLoc());
                                
                                //System.out.println(view.getBoardGraph().getVertexById(x,y));
                                checkForMonsterActivations();
                                
                                //System.out.println(y);
                            }
                            else    {
                                System.out.println("You cannot move through walls!");
                            }
                        }
                        if(diceRoll == 0)   {
                           frame.setVisible(false); 
                        }
                        //System.out.println(diceRoll);
                    }
                    catch(IndexOutOfBoundsException ex)   {
                        
                    }
            }
        });
        JButton left = new JButton("Left"); 
        left.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Left!");
                    try {
                        if(x == 0)  {
                            System.out.println("Out of bounds");
                        }
                        else if(diceRoll != 0 || x != 0)  {
                            if(board.getBoardGraph().getVertexById(x, y).hasNeighbor(board.getBoardGraph().getVertexById(x-1, y)) && board.getBoardGraph().getVertexById(x-1, y).getValue() instanceof Tile )  {
                                board.getBoardGraph().getVertexById(x-1, y).setValue(hero);
                                board.getBoardGraph().getVertexById(x, y).setValue(new Tile());
                                diceRoll--;
                                moveLabel.setText("Movement Points: " + diceRoll);
                                board.update(x,y);
                                board.update(x-1,y);
                                x = x - 1;
                                heroList.get(moveHeroValue).setXLoc(x);
                                
                                //System.out.println(view.getBoardGraph().getVertexById(x,y));
                                checkForMonsterActivations();
                            }
                            else    {
                                System.out.println("You cannot move through walls!");
                            }
                        }
                        if(diceRoll == 0)   {
                           frame.setVisible(false); 
                        }
                        
                        //System.out.println(diceRoll);
                    }
                    catch(IndexOutOfBoundsException ex)   {
                        
                    }
            }
        });
        JButton right = new JButton("Right");
        right.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Right!");
                    try {
                        if(x == 25) {
                            System.out.println("Out of bounds");
                        }
                        else if(diceRoll != 0)  {
                            if(board.getBoardGraph().getVertexById(x, y).hasNeighbor(board.getBoardGraph().getVertexById(x+1, y)) && board.getBoardGraph().getVertexById(x+1, y).getValue() instanceof Tile)  {
                                board.getBoardGraph().getVertexById(x+1, y).setValue(hero);
                                board.getBoardGraph().getVertexById(x, y).setValue(new Tile());
                                diceRoll--;
                                moveLabel.setText("Movement Points: " + diceRoll);
                                board.update(x,y);
                                board.update(x+1, y);
                                x = x + 1;
                                heroList.get(moveHeroValue).setXLoc(x);
                                System.out.println("The Barbar is now mapped to " + heroList.get(moveHeroValue).getXLoc() + " " + heroList.get(moveHeroValue).getYLoc());
                                
                                //System.out.println(view.getBoardGraph().getVertexById(x,y));
                                checkForMonsterActivations();
                                //board.updateTile(x, y, x+1, y);
                                
                            }
                            else    {
                                System.out.println("You cannot move through walls!");
                            }
                        }
                        if(diceRoll == 0)   {
                           frame.setVisible(false); 
                        }
                        //System.out.println(diceRoll);
                    }
                    catch(IndexOutOfBoundsException ex)   {
                        
                    }
            }
        });
        JButton end = new JButton("End");
        end.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                System.out.println("You chose to stop your movement!");
                diceRoll = 0;
                frame.setVisible(false);
            }
        });
        contentPane.add(up, BorderLayout.NORTH);
        contentPane.add(down, BorderLayout.SOUTH);
        contentPane.add(left, BorderLayout.WEST);
        contentPane.add(right, BorderLayout.EAST);
        contentPane.add(end, BorderLayout.CENTER);
        frame.pack();
        frame.repaint();
        frame.setResizable(false);
        frame.setVisible(true);                            
    }
    /**
     * Checks to see if a room is "opened" and then activates all the monsters inside said room
     */
    public static void checkForMonsterActivations() {
        //System.out.println("The size of monsterList " + monsterList.size());
        //System.out.println("X is " + x +" and Y is " + y);
        for(int i = 0; i < monsterList.size(); i++) {
            if(!tortureRoomFlag) {  
                //System.out.println("Inside the tortureRoomFlag");
            //Torture Rack Room with Goblin and Orc From Hallway (Bottom Left)
                if(x == 0 && y == 11)   {
                    if(monsterList.get(i).getXLoc() > 0 && monsterList.get(i).getXLoc() < 5)    {
                        if(monsterList.get(i).getYLoc() > 9 && monsterList.get(i).getYLoc() < 14)  {                 
                            monsterList.get(i).setActivated(true);
                            System.out.println("Monster at " + monsterList.get(i).getXLoc() + " " + monsterList.get(i).getYLoc() + " Activated");
                            tortureRoomCount++;
                            if(tortureRoomCount == 2) {
                                tortureRoomFlag = true;
                            }
                        }
                    }
                }
            }
            if(!studyRoomFlag)   {    
                //System.out.println("Inside the studyRoomFlag");
                //Study Desk Room with Two orcs From Hallway
                if(x == 7 && y == 18)   {
                    //System.out.println("Inside x and y check");
                    //System.out.println("Monsters X Loc " + monsterList.get(i).getXLoc());
                    //System.out.println("Monsters Y Loc " + monsterList.get(i).getYLoc());
                    if(monsterList.get(i).getXLoc() > 4 && monsterList.get(i).getXLoc() < 9)    {                    
                        if(monsterList.get(i).getYLoc() > 12 && monsterList.get(i).getYLoc() < 18)  {
                            monsterList.get(i).setActivated(true);
                            System.out.println("Monster at " + monsterList.get(i).getXLoc() + " " + monsterList.get(i).getYLoc() + " Activated");
                            studyRoomCount++;
                            if(studyRoomCount == 2) {
                                studyRoomFlag = true;
                            }
                        }
                    }
                }
                //Study Desk Room with Two Orcs from Weapon Rack Room with Fimir and Goblin
                if(x == 9 && y == 15)   {
                    if(monsterList.get(i).getXLoc() > 4 && monsterList.get(i).getXLoc() < 9)    {
                        if(monsterList.get(i).getYLoc() > 12 && monsterList.get(i).getYLoc() < 18)  {
                            monsterList.get(i).setActivated(true);
                            System.out.println("Monster at " + monsterList.get(i).getXLoc() + " " + monsterList.get(i).getYLoc() + " Activated");
                            studyRoomCount++;
                            if(studyRoomCount == 2) {
                                studyRoomFlag = true;
                            }
                        }
                    }
                }
            }
            if(!weaponRackRoomFlag)  {
            //Weapon Rack Room with Fimir and Goblin from Hallway
                if(x == 10 && y == 12)  {
                    if(monsterList.get(i).getXLoc() > 8 && monsterList.get(i).getXLoc() < 12)    {
                        if(monsterList.get(i).getYLoc() > 12 && monsterList.get(i).getYLoc() < 18)  {
                            monsterList.get(i).setActivated(true);
                            System.out.println("Monster at " + monsterList.get(i).getXLoc() + " " + monsterList.get(i).getYLoc() + " Activated");
                            weaponRackRoomCount++;
                            if(weaponRackRoomCount == 2) {
                                weaponRackRoomFlag = true;
                            }
                        }
                    }
                }
                if(x == 8 && y == 15)  {
                    if(monsterList.get(i).getXLoc() > 8 && monsterList.get(i).getXLoc() < 12)    {
                        if(monsterList.get(i).getYLoc() > 12 && monsterList.get(i).getYLoc() < 18)  {
                            monsterList.get(i).setActivated(true);
                            System.out.println("Monster at " + monsterList.get(i).getXLoc() + " " + monsterList.get(i).getYLoc() + " Activated");
                            weaponRackRoomCount++;
                            if(weaponRackRoomCount == 2) {
                                weaponRackRoomFlag = true;
                            }
                        }
                    }
                }
            }
            if(!shelvesRoomFlag) {
                //Shelves Room with 2 Chaos Warriors
                if(x == 13 && y == 15)  {
                    if(monsterList.get(i).getXLoc() > 13 && monsterList.get(i).getXLoc() < 18)    {
                        if(monsterList.get(i).getYLoc() > 12 && monsterList.get(i).getYLoc() < 18)  {
                            monsterList.get(i).setActivated(true);
                            System.out.println("Monster at " + monsterList.get(i).getXLoc() + " " + monsterList.get(i).getYLoc() + " Activated");
                           shelvesRoomCount++;
                            if(shelvesRoomCount == 2) {
                                shelvesRoomFlag = true;
                            }
                        }
                    }
                }
            }
            if(!middleRoomFlag) {
                //Middle Room with Gargoyle, Chaos Warrior, and Two Orcs
                if(x== 16 && y == 9)    {
                    if(monsterList.get(i).getXLoc() > 9 && monsterList.get(i).getXLoc() < 16)    {
                        if(monsterList.get(i).getYLoc() > 6 && monsterList.get(i).getYLoc() < 12)  {
                            monsterList.get(i).setActivated(true);
                            System.out.println("Monster at " + monsterList.get(i).getXLoc() + " " + monsterList.get(i).getYLoc() + " Activated");
                            middleRoomCount++;
                            if(middleRoomCount == 4) {
                                middleRoomFlag = true;
                            }
                        }
                    }
                }
            }
            if(!spellBookRoomFlag)   {
                //Spell Book Room with Goblin and Orc from Hallway (Top Left)
                if(x == 3 && y == 9)    {
                    if(monsterList.get(i).getXLoc() > 0 && monsterList.get(i).getXLoc() < 5)    {
                        if(monsterList.get(i).getYLoc() > 3 && monsterList.get(i).getYLoc() < 9)  {
                            monsterList.get(i).setActivated(true);
                            System.out.println("Monster at " + monsterList.get(i).getXLoc() + " " + monsterList.get(i).getYLoc() + " Activated");
                            spellBookRoomCount++;
                            if(spellBookRoomCount == 2) {
                                spellBookRoomFlag = true;
                            }
                        }
                    }
                }
            }
            if(!tableRoomFlag)   {
                //Table Room with two Goblins from Hallway (Top Left)
                if(x == 7 && y == 9)    {
                    if(monsterList.get(i).getXLoc() > 4 && monsterList.get(i).getXLoc() < 9)    {
                        if(monsterList.get(i).getYLoc() > 3 && monsterList.get(i).getYLoc() < 9)  {
                            monsterList.get(i).setActivated(true);
                            System.out.println("Monster at " + monsterList.get(i).getXLoc() + " " + monsterList.get(i).getYLoc() + " Activated");
                            tableRoomCount++;
                            if(tableRoomCount == 2) {
                                tableRoomFlag = true;
                            }
                        }
                    }
                }
            }
            if(!emptySkeletonRoomFlag)   {
                //Empty room with Two skeletons from Spell Book Room with Orc and Goblin (Top Left)
                if(x == 3 && y == 4)    {
                    if(monsterList.get(i).getXLoc() > 0 && monsterList.get(i).getXLoc() < 5)    {
                        if(monsterList.get(i).getYLoc() > 0 && monsterList.get(i).getYLoc() < 4)  {
                            monsterList.get(i).setActivated(true);
                            System.out.println("Monster at " + monsterList.get(i).getXLoc() + " " + monsterList.get(i).getYLoc() + " Activated");
                            emptySkeletonRoomCount++;
                            if(emptySkeletonRoomCount == 2) {
                                emptySkeletonRoomFlag = true;
                            }
                        }
                    }
                }
            }
            if(!emptyZombieRoomFlag) {
                //Empty room with Two Zombies and a Mummy from Empty Room with two skeletons
                if(x == 4 && y == 2)    {
                    if(monsterList.get(i).getXLoc() > 4 && monsterList.get(i).getXLoc() < 9)    {
                        if(monsterList.get(i).getYLoc() > 0 && monsterList.get(i).getYLoc() < 4)  {
                            monsterList.get(i).setActivated(true);
                            System.out.println("Monster at " + monsterList.get(i).getXLoc() + " " + monsterList.get(i).getYLoc() + " Activated");
                            emptyZombieRoomCount++;
                            if(emptyZombieRoomCount == 3) {
                                emptyZombieRoomFlag = true;
                            }
                        }
                    }
                }
            }
            if(!tombRoomFlag)    {
                //Tomb Room with a Mummy and two Skeletons from Empty Room with two Zombies and a Mummy
                if(x == 8 && y == 2)    {
                    if(monsterList.get(i).getXLoc() > 8 && monsterList.get(i).getXLoc() < 12)    {
                        if(monsterList.get(i).getYLoc() > 0 && monsterList.get(i).getYLoc() < 6)  {
                            monsterList.get(i).setActivated(true);
                            System.out.println("Monster at " + monsterList.get(i).getXLoc() + " " + monsterList.get(i).getYLoc() + " Activated");
                            tombRoomCount++;
                            if(tombRoomCount == 3) {
                                tombRoomFlag = true;
                            }
                        }
                    }
                }
            }
        }
    }
    public static int heroCombat( AbstractCharacter hero ,GameBoard board)    {
        Tester.targetFlag = false;
        for(int i = 0; i < 19; i++)    {
            for(int j = 0; j < 26; j++)    {
                if(board.getBoardGraph().getVertexById(j, i).getValue() == hero)    {
                    //System.out.println(board.getBoardGraph().getVertexById(j, i));
                    Tester.x = j;
                    Tester.y = i;
                }
            }
        }
        System.out.println("The hero's x y values are " + Tester.x + Tester.y);
        if(containsInstanceOfMonster(board.getBoardGraph(), Tester.x, Tester.y)) {
            Tester.damageValue = hero.attack() - Tester.monsterList.get(Tester.indexLoc).defend();
            if(Tester.damageValue < 0)  {
                Tester.damageValue = 0;
            }
            
            System.out.println("The Current Damage dealt is: " + Tester.damageValue);
            Tester.monsterList.get(Tester.indexLoc).setCurrentHitPoints(Tester.monsterList.get(Tester.indexLoc).getCurrentHitPoints()-Tester.damageValue);
            if(Tester.monsterList.get(Tester.indexLoc).getCurrentHitPoints() < 1)  {              
                board.getBoardGraph().getVertexById(Tester.xMonst,Tester.yMonst).setValue(new Tile());
                view.update(xMonst, yMonst);
                updateInfoPanel();
                Tester.monsterList.remove(Tester.indexLoc);
            }
            return Tester.damageValue;
         }
        else    {
            System.out.println("No Monsters detected within range");
        }
        return 0;
        
    }
/*
    public void findMonsterType(GameBoard board)   {
        if(board.getBoardGraph().getVertexById(this.xMonst, this.yMonst).getValue() instanceof Goblin)    {
                    this.monster = new Goblin();
                    System.out.println("Target acquired for: " + this.monster);
        }
        if(board.getBoardGraph().getVertexById(this.xMonst, this.yMonst).getValue() instanceof Orc)    {
                    this.monster = new Orc();
                    System.out.println("Target acquired for: " + this.monster);
        }
        else    {
            System.out.println("No target acquired");
        }
    }
*/
    /*JFrame targetFrame = new JFrame("Target Choices");
            targetFrame.setSize(215,250);
            targetFrame.setResizable(false);
            targetFrame.setLayout(new BorderLayout());
            JPanel subFrame = new JPanel();
            for(int i = 0; i < this.targetMonsterList.size(); i++)  {
                this.loopValue = i;
                if(this.targetMonsterList.get(i).getXLoc() == this.x+1) {
                    JButton right = new JButton("Right");
                    right.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            indexLoc = loopValue;
                            targetFlag = true;
                        }
                    });
                    subFrame.add(right);
                }
                if(this.targetMonsterList.get(i).getXLoc() == this.x-1) {
                    JButton left = new JButton("Left");
                    left.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            indexLoc = loopValue;
                            targetFlag = true;
                        }
                    });
                    subFrame.add(left);
                }
                if(this.targetMonsterList.get(i).getYLoc() == this.y+1) {
                    JButton down = new JButton("Down");
                    down.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            indexLoc = loopValue;
                            targetFlag = true;
                        }
                    });
                    subFrame.add(down);
                }
                if(this.targetMonsterList.get(i).getYLoc() == this.y-1) {
                    JButton up = new JButton("Up");
                    up.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            indexLoc = loopValue;
                            targetFlag = true;
                        }
                    });
                    subFrame.add(up);
                }
            }
            targetFrame.add(subFrame);
            targetFrame.setVisible(true);
            //findMonsterType(board);
    */
    public static boolean containsInstanceOfMonster(Graph gameBoard, int x, int y)  {
        //Empty the current Target List so that there is only up to 4 items at a time
        Tester.targetMonsterList.clear();
        Tester.flag = false;
        if(x != 25) {
            if(gameBoard.getVertexById(x+1, y).getValue() instanceof AbstractMonster && gameBoard.getVertexById(x, y).hasNeighbor(gameBoard.getVertexById(x+1, y)))    {
                Tester.xMonst = Tester.x + 1;
                Tester.yMonst = Tester.y;
                System.out.println("The x value is " + Tester.xMonst + " and the y value is " + Tester.yMonst);
                //System.out.println("The size of the Monster List is " + this.monsterList.size());
                for(int i = 0; i < Tester.monsterList.size(); i++)    {
                    //System.out.println("The i value is currently" + this.i);
                    System.out.println(Tester.monsterList.get(i) + " and its x and y values are " + Tester.monsterList.get(i).getXLoc() + " " + Tester.monsterList.get(i).getYLoc());
                    if(Tester.monsterList.get(i).getXLoc() == Tester.x+1 && Tester.monsterList.get(i).getYLoc() == Tester.y) {
                        Tester.indexLoc = i;
                        Tester.targetMonsterList.add(Tester.monsterList.get(i));
                    }
                }
                Tester.flag = true;
            }
            else    {
                if(Tester.targetMonsterList.size() == 0)    {
                    Tester.flag = false;
                }
            }
        }
        if(x != 0) {
            if(gameBoard.getVertexById(x-1, y).getValue() instanceof AbstractMonster && gameBoard.getVertexById(x, y).hasNeighbor(gameBoard.getVertexById(x-1, y)))    {
                Tester.xMonst = Tester.x - 1;
                Tester.yMonst = Tester.y;
                for(int i = 0; i < Tester.monsterList.size(); i++)    {
                    if(Tester.monsterList.get(i).getXLoc() == Tester.x-1 && Tester.monsterList.get(i).getYLoc() == Tester.y) {
                        Tester.indexLoc = i;
                        Tester.targetMonsterList.add(Tester.monsterList.get(i));
                    }
                }
                Tester.flag = true;
            }
            else    {
                if(Tester.targetMonsterList.size() == 0)    {
                    Tester.flag = false;
                }
            }
        }
        if(y != 18) {
            if(gameBoard.getVertexById(x, y+1).getValue() instanceof AbstractMonster && gameBoard.getVertexById(x, y).hasNeighbor(gameBoard.getVertexById(x, y+1)))    {
                Tester.xMonst = Tester.x;
                Tester.yMonst = Tester.y+1;
                System.out.println("The x value is " + Tester.xMonst + " and the y value is " + Tester.yMonst);
                for(int i = 0; i < Tester.monsterList.size(); i++)    {
                    if(Tester.monsterList.get(i).getXLoc() == Tester.x && Tester.monsterList.get(i).getYLoc() == Tester.y+1) {
                        Tester.indexLoc = i;
                        Tester.targetMonsterList.add(Tester.monsterList.get(i));
                    }
                }
                Tester.flag = true;
            }
            else    {
                if(Tester.targetMonsterList.size() == 0)    {
                    Tester.flag = false;
                }
            }
        }
        if(y != 0)  {
            if(gameBoard.getVertexById(x, y-1).getValue() instanceof AbstractMonster && gameBoard.getVertexById(x, y).hasNeighbor(gameBoard.getVertexById(x, y-1)))    {
                Tester.xMonst = Tester.x;
                Tester.yMonst = Tester.y-1;
                for(int i = 0; i < Tester.monsterList.size(); i++)    {
                    if(Tester.monsterList.get(i).getXLoc() == Tester.x && Tester.monsterList.get(i).getYLoc() == Tester.y-1) {
                        Tester.indexLoc = i;
                        Tester.targetMonsterList.add(Tester.monsterList.get(i));
                    }
                }
                Tester.flag = true;
            }
            else    {
                if(Tester.targetMonsterList.size() == 0)    {
                    Tester.flag = false;
                }
            }
        }
        
        return flag;
    }
    public static int monsterCombat(GameBoard board, AbstractMonster monster )    {
        Tester.targetFlag = false;
        for(int i = 0; i < 19; i++)    {
            for(int j = 0; j < 26; j++)    {
                if(board.getBoardGraph().getVertexById(j, i).getValue() == monster)    {
                    //System.out.println(board.getBoardGraph().getVertexById(j, i));
                    Tester.x = j;
                    Tester.y = i;
                    //System.out.println("The Y value is " + Tester.y);
                   // System.out.println("The X value is " + Tester.x);
                }
            }
        }
        //System.out.println("The Monster's x y values are " + Tester.x + Tester.y);
        if(containsInstanceOfHero(board.getBoardGraph(), Tester.x, Tester.y)) {
            randomAttackSelect = (int) (Math.random()*targetHeroList.size()+0);
            Tester.damageValue = monster.attack() - Tester.targetHeroList.get(randomAttackSelect).defend();
            if(Tester.damageValue < 0)  {
                Tester.damageValue = 0;
            }
            
            //System.out.println("The Current Damage dealt is: " + Tester.damageValue);
            if(targetHeroList.get(randomAttackSelect) instanceof Barbarian)  {
                //System.out.println("The monster chose to hit the Barbarian");
                searchForHero(barbarian);
            }
            if(targetHeroList.get(randomAttackSelect) instanceof Wizard)  {
                //System.out.println("The monster chose to hit the wizard");
                searchForHero(wizard);
            }
            if(targetHeroList.get(randomAttackSelect) instanceof Elf)  {
                //System.out.println("The monster chose to hit the elf");
                searchForHero(elf);
            }
            if(targetHeroList.get(randomAttackSelect) instanceof Dwarf)  {
                //System.out.println("The monster chose to hit the dwarf");
                searchForHero(dwarf);
            }
            //help
            if(heroList.get(moveHeroValue) instanceof Barbarian)  {
                System.out.println("The random attack maps to Barbarian");
            }
            if(heroList.get(moveHeroValue) instanceof Dwarf)  {
                System.out.println("The random attack maps to Dwarf");
            }
            if(heroList.get(moveHeroValue) instanceof Elf)  {
                System.out.println("The random attack maps to Elf");
            }
            if(heroList.get(moveHeroValue) instanceof Wizard)  {
                System.out.println("The random attack maps to Wizard");
            }
            Tester.heroList.get(moveHeroValue).setCurrentHitPoints(Tester.heroList.get(moveHeroValue).getCurrentHitPoints()-Tester.damageValue);
            if(Tester.heroList.get(moveHeroValue).getCurrentHitPoints() < 1)  {
                board.getBoardGraph().getVertexById(Tester.xHero,Tester.yHero).setValue(new Tile());
                view.update(xHero, yHero);
                updateInfoPanel();
                Tester.heroList.get(moveHeroValue).setXLoc(30);
                Tester.heroList.get(moveHeroValue).setYLoc(30);
                Tester.heroList.remove(moveHeroValue);
                
            }
            //view.update();
            updateInfoPanel();
            return Tester.damageValue;
         }
        else    {
            System.out.println("No Heroes detected within range");
        }
        return 0;
    }
    public static boolean containsInstanceOfHero(Graph gameBoard, int x, int y)  {
        //Empty the current Target List so that there is only up to 4 items at a time
        Tester.targetHeroList.clear();
        Tester.flag = false;
        if(x != 25) {
            if(gameBoard.getVertexById(x+1, y).getValue() instanceof AbstractCharacter && gameBoard.getVertexById(x, y).hasNeighbor(gameBoard.getVertexById(x+1, y)))    {
                //System.out.println("A hero is to the right of this monster");
                Tester.xHero = x + 1;
                Tester.yHero = y;
                //System.out.println("The x value is " + Tester.xHero + " and the y value is " + Tester.yHero);
                //System.out.println("The size of the Monster List is " + this.monsterList.size());
                for(int i = 0; i < Tester.heroList.size(); i++)    {
                    //System.out.println("The i value is currently" + this.i);
                    System.out.println(Tester.heroList.get(i) + " and its x and y values are " + Tester.heroList.get(i).getXLoc() + " " + Tester.heroList.get(i).getYLoc());
                    if(Tester.heroList.get(i).getXLoc() == x+1 && Tester.heroList.get(i).getYLoc() == y) {
                        Tester.indexLoc = i;
                        Tester.targetHeroList.add(Tester.heroList.get(i));
                    }
                }
                Tester.flag = true;
            }
            else    {
                if(Tester.targetHeroList.size() == 0)    {
                    Tester.flag = false;
                }
            }
        }
        if(x != 0) {
            if(gameBoard.getVertexById(x-1, y).getValue() instanceof AbstractCharacter && gameBoard.getVertexById(x, y).hasNeighbor(gameBoard.getVertexById(x-1, y)))    {
               //System.out.println("A hero is to the left of this monster");
                Tester.xHero = x - 1;
                Tester.yHero = y;
                for(int i = 0; i < Tester.heroList.size(); i++)    {
                    if(Tester.heroList.get(i).getXLoc() == x-1 && Tester.heroList.get(i).getYLoc() == y) {
                        Tester.indexLoc = i;
                        Tester.targetHeroList.add(Tester.heroList.get(i));
                    }
                }
                Tester.flag = true;
            }
            else    {
                if(Tester.targetHeroList.size() == 0)    {
                    Tester.flag = false;
                }
            }
        }
        if(y != 18) {
            if(gameBoard.getVertexById(x, y+1).getValue() instanceof AbstractCharacter && gameBoard.getVertexById(x, y).hasNeighbor(gameBoard.getVertexById(x, y+1)))    {
                //System.out.println("A hero is to the south of this monster");
                Tester.xHero = x;
                Tester.yHero = y+1;
                //System.out.println("The x value is " + Tester.xHero + " and the y value is " + y);
                
                for(int i = 0; i < Tester.heroList.size(); i++)    {
                    //System.out.println("The Tester.y position is " + y);
                    //System.out.println("The Hero at location " + i + "'s X location is " + Tester.heroList.get(i).getXLoc());
                    //System.out.println("The Tester.x position is " + x);
                    //System.out.println("The Hero at location " + i + "'s Y location is " + Tester.heroList.get(i).getYLoc());
                    //System.out.println("The Tester.y position is " + y);
                    if(Tester.heroList.get(i).getXLoc() == x && Tester.heroList.get(i).getYLoc() == y+1) {
                        //System.out.println("Inside");
                        Tester.indexLoc = i;
                        Tester.targetHeroList.add(Tester.heroList.get(i));
                    }
                }
                Tester.flag = true;
            }
            else    {
                if(Tester.targetHeroList.size() == 0)    {
                    Tester.flag = false;
                }
            }
        }
        if(y != 0)  {
            if(gameBoard.getVertexById(x, y-1).getValue() instanceof AbstractCharacter && gameBoard.getVertexById(x, y).hasNeighbor(gameBoard.getVertexById(x, y-1)))    {
                //System.out.println("A hero is to the north of this monster");
                Tester.xHero = x;
                Tester.yHero = y-1;
                for(int i = 0; i < Tester.heroList.size(); i++)    {
                    if(Tester.heroList.get(i).getXLoc() == x && Tester.heroList.get(i).getYLoc() == y-1) {
                        //System.out.println("Inside the check, the size of target list is increased");
                        Tester.indexLoc = i;
                        Tester.targetHeroList.add(Tester.heroList.get(i));
                    }
                }
                Tester.flag = true;
                
               // System.out.println("The flag in north is " + Tester.flag);
            }
            else    {
                if(Tester.targetHeroList.size() == 0)    {
                    Tester.flag = false;
                }
            }
        }
        
        return flag;
    }
    public static void shortestPath(AbstractMonster monster, GameBoard board)  {
        pathways.clear();
        int xHeroLoc = 0;
        int yHeroLoc = 0;
        int xMonsterLoc;
        int yMonsterLoc;
        int neighborSize;
        int count = 0;
        //Gather the monsters movement points in a seperate variable
        int movementPoints = monster.getMovementPoints();
        //Gather the monster's x and y value
        xMonsterLoc = monster.getXLoc();
        yMonsterLoc = monster.getYLoc();
        //System.out.println("Here is the monster x value " + xMonsterLoc);
        //System.out.println("Here is the monster y value " + yMonsterLoc);
        //Find the Hero and determine if the monster is already within attacking distance
        /*for(int i = 0; i < 19; i++)    {
            for(int j = 0; j < 26; j++)    {
                if(board.getBoardGraph().getVertexById(j, i).getValue() instanceof AbstractCharacter)    {
                    //System.out.println(board.getBoardGraph().getVertexById(j, i));
                    xHeroLoc = j;
                    yHeroLoc = i;
                    //System.out.println("Here is the hero x value " + xHeroLoc);
                    //System.out.println("Here is the hero y value " + yHeroLoc);
                    if(board.getBoardGraph().getVertexById(xHeroLoc, yHeroLoc).hasNeighbor(board.getBoardGraph().getVertexById(xMonsterLoc, yMonsterLoc)))    {
                            System.out.println("Why move if the " + monster.toString() + " is next to a hero");
                            movementPoints = 0;
                        }
                }
            }
        }*/
        for(int i = 0; i < heroList.size(); i++)    {
            if(board.getBoardGraph().getVertexById(heroList.get(i).getXLoc(), heroList.get(i).getYLoc()).hasNeighbor(board.getBoardGraph().getVertexById(xMonsterLoc, yMonsterLoc)))    {
                            System.out.println("Why move if the " + monster.toString() + " is next to a hero");
                            movementPoints = 0;
            }
        }
        if(movementPoints != 0) {
            //Get the neighbor size of the original position
            neighborSize = board.getBoardGraph().getVertexById(xMonsterLoc, yMonsterLoc).getNeighbors().size();
            //System.out.println(neighborSize);
            //Find the initial # of paths and add it to the pathways list
            for(int i = 0; i < neighborSize; i++) {
                pathways.add(new ArrayList<Vertex<Tile>>());
                pathways.get(i).add((Vertex<Tile>) board.getBoardGraph().getVertexById(xMonsterLoc, yMonsterLoc).getNeighbors().get(i));
                //System.out.println(board.getBoardGraph().getVertexById(xMonsterLoc, yMonsterLoc).getNeighbors().get(i));
            }
            //Just testing out how many original Paths there are
            //System.out.println(pathways.size());
            for(int i = 0; i < pathways.size(); i ++) {
                for(int j = 0; j < pathways.get(i).size(); j++) {
                    //System.out.println(pathways.get(i).get(j));
                }
            }
            
            
            //pathways.get(0).add(board.getBoardGraph().getVertexById(xHeroLoc, yHeroLoc));
            //System.out.println(pathways.get(0).get(1));
            outerloop:
            //while(!pathways.get(0).get(pathways.get(0).size()-1).equalsIgnoreIndividualNeighbors(board.getBoardGraph().getVertexById(xHeroLoc, yHeroLoc)) )   {
                middleloop:
                for(pathwayFindValue = 0; pathwayFindValue < pathways.size(); pathwayFindValue ++)   {
                    System.out.println("I is " + pathwayFindValue);
                    newPathwayCreationCount = pathwayFindValue + neighborSize-1;
                    for(int j = 0; j < pathways.get(pathwayFindValue).size(); j++) {
                        System.out.println("J is " + j);
                        //System.out.println(pathways.get(i).get(j));
                        //Two neighbors, but only one is accessible because a player is on it
                        System.out.println(pathways.get(pathwayFindValue).get(j).getNeighbors().size());
                        if(pathways.get(pathwayFindValue).get(j).getNeighbors().size() == 2 && pathways.get(pathwayFindValue).get(j).getNeighbors().get(0).getValue() instanceof Tile && !(pathways.get(pathwayFindValue).get(j).getNeighbors().get(1).getValue() instanceof Tile))   {
                            
                        }
                        //Two neighbors, but only one is accessible because a player is on it
                        if(pathways.get(pathwayFindValue).get(j).getNeighbors().size() == 2 && !(pathways.get(pathwayFindValue).get(j).getNeighbors().get(0).getValue() instanceof Tile) && pathways.get(pathwayFindValue).get(j).getNeighbors().get(1).getValue() instanceof Tile)   {
                            
                        }
                        
                        //Two neighbors with both being empty tiles
                        if(pathways.get(pathwayFindValue).get(j).getNeighbors().size() == 2)   {
                            System.out.println(pathways.get(pathwayFindValue).get(j));
                            System.out.println("Values for i = " + pathwayFindValue + " and j = " + j + "has only two neighbors");
                            for(int k = 0; k < 2; k++)  {
                                if(pathways.get(pathwayFindValue).contains(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k)) || pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).equalsIgnoreIndividualNeighbors(board.getBoardGraph().getVertexById(xMonsterLoc, yMonsterLoc)))  {
                                    System.out.println("The duplicate value");
                                    System.out.println(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k));
                                    System.out.println("This is the source or is already inside the path");
                                }
                                else    {
                                    System.out.println("The non duplicate neighbor");
                                    if(!(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).getValue() instanceof Tile))    {
                                        
                                        System.out.println("But the one to branch off of is already taken by another character or monster");
                                        if(board.getBoardGraph().getVertexById(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).getx(), pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).gety()).getValue() instanceof AbstractCharacter)   {
                                            System.out.println("But it's the hero so we are done");
                                            monsterMovementFlag = true;
                                            break outerloop;
                                        }
                                    }
                                    else    {
                                        System.out.println(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k));
                                        pathways.get(pathwayFindValue).add(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k));
                                        System.out.println(pathways.get(0).get(pathways.get(0).size()-1).equalsIgnoreIndividualNeighbors(board.getBoardGraph().getVertexById(xHeroLoc, yHeroLoc)));
                                    }
                                }
                            }

                        }
                        if(pathways.get(pathwayFindValue).get(j).getNeighbors().size() == 3)   {
                            System.out.println(pathways.get(pathwayFindValue).get(j));
                            System.out.println("Three neighbors");
                            for(int k = 0; k < 3; k++)  {
                                if(pathways.get(pathwayFindValue).contains(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k)) || pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).equalsIgnoreIndividualNeighbors(board.getBoardGraph().getVertexById(xMonsterLoc, yMonsterLoc)))  {
                                    System.out.println("The duplicate value");
                                    System.out.println(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k));
                                    System.out.println("This is the source or is already inside the path");
                                }
                                else if(count != 0)  {
                                    System.out.println("The one to start a new path based off of the old path");
                                    if(!(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).getValue() instanceof Tile))    {
                                        System.out.println("But the one to start a new path off of the old path is already taken by another character or monster");
                                        if(board.getBoardGraph().getVertexById(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).getx(), pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).gety()).getValue() instanceof AbstractCharacter)   {
                                            System.out.println("But it's the hero so we are done");
                                            pathways.get(pathwayFindValue).remove(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k-1));
                                            monsterMovementFlag = true;
                                            break outerloop;
                                        }
                                    }
                                    else    {
                                        System.out.println(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k));
                                        pathways.add(new ArrayList<Vertex<Tile>>(pathways.get(pathwayFindValue).size()));
                                        newPathwayCreationCount++;
                                        for(Vertex v: pathways.get(pathwayFindValue))     {
                                            pathways.get(newPathwayCreationCount).add(v.cloneVertex());
                                        }
                                        System.out.println(pathways.get(newPathwayCreationCount).size());
                                        pathways.get(newPathwayCreationCount).add(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k));
                                    }
                                }
                                else if(count == 0)  {
                                    System.out.println("The one to branch off of this path");
                                    if(!(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).getValue() instanceof Tile))    {
                                        
                                        System.out.println("But the one to branch off of is already taken by another character or monster");
                                        if(board.getBoardGraph().getVertexById(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).getx(), pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).gety()).getValue() instanceof AbstractCharacter)   {
                                            System.out.println("But it's the hero so we are done");
                                            monsterMovementFlag = true;
                                            break outerloop;
                                        }
                                    }
                                    else    {
                                        System.out.println(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k));
                                        pathways.get(pathwayFindValue).add(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k));
                                        count++;
                                        System.out.println(pathways.get(0).get(pathways.get(0).size()-1).equalsIgnoreIndividualNeighbors(board.getBoardGraph().getVertexById(xHeroLoc, yHeroLoc)));
                                    }
                                }


                            }
                            count = 0;
                        }
                        if(pathways.get(pathwayFindValue).get(j).getNeighbors().size() == 4)   {
                            System.out.println("Four neighbors");
                            for(int k = 0; k < 4; k++)  {
                                if(pathways.get(pathwayFindValue).contains(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k)) || pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).equalsIgnoreIndividualNeighbors(board.getBoardGraph().getVertexById(xMonsterLoc, yMonsterLoc)))  {
                                    System.out.println("The duplicate value");
                                    System.out.println(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k));
                                    System.out.println("This is the source or is already inside the path");
                                }
                                else if(count == 2)  {
                                    System.out.println("The second to start a new path based off of the old path");
                                    if(!(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).getValue() instanceof Tile))    {
                                        System.out.println("But the one to start a new path off of the old path is already taken by another character or monster");
                                        if(board.getBoardGraph().getVertexById(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).getx(), pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).gety()).getValue() instanceof AbstractCharacter)   {
                                            System.out.println("But it's the hero so we are done");
                                            pathways.get(pathwayFindValue).remove(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k-1));
                                            monsterMovementFlag = true;
                                            break outerloop;
                                        }
                                    }
                                    else    {
                                        System.out.println(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k));
                                        pathways.add(new ArrayList<Vertex<Tile>>(pathways.get(pathwayFindValue).size()));
                                        newPathwayCreationCount++;
                                        for(Vertex v: pathways.get(pathwayFindValue))     {
                                            pathways.get(newPathwayCreationCount).add(v.cloneVertex());
                                        }
                                        System.out.println(pathways.get(newPathwayCreationCount).size());
                                            pathways.get(newPathwayCreationCount).add(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k));
                                    }
                                }
                                else if(count == 1)  {
                                    System.out.println("The one to start a new path based off of the old path");
                                    if(!(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).getValue() instanceof Tile))    {
                                        System.out.println("But the one to start a new path off of the old path is already taken by another character or monster");
                                        if(board.getBoardGraph().getVertexById(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).getx(), pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).gety()).getValue() instanceof AbstractCharacter)   {
                                            System.out.println("But it's the hero so we are done");
                                            pathways.get(pathwayFindValue).remove(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k-1));
                                            monsterMovementFlag = true;
                                            break outerloop;
                                        }
                                    }
                                    else    {
                                        System.out.println(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k));
                                        pathways.add(new ArrayList<Vertex<Tile>>(pathways.get(pathwayFindValue).size()));
                                        newPathwayCreationCount++;
                                        for(Vertex v: pathways.get(pathwayFindValue))     {
                                            pathways.get(pathwayFindValue+1).add(v.cloneVertex());
                                        }
                                        System.out.println(pathways.get(newPathwayCreationCount).size());
                                        pathways.get(newPathwayCreationCount).add(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k));
                                        count++;
                                    }
                                }
                                else if(count == 0)  {
                                    System.out.println("The one to branch off of this path");
                                    if(!(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).getValue() instanceof Tile))    {
                                        
                                        System.out.println("But the one to branch off of is already taken by another character or monster");
                                        if(board.getBoardGraph().getVertexById(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).getx(), pathways.get(pathwayFindValue).get(j).getNeighbors().get(k).gety()).getValue() instanceof AbstractCharacter)   {
                                            System.out.println("But it's the hero so we are done");
                                            monsterMovementFlag = true;
                                            break outerloop;
                                        }
                                    }
                                    else    {
                                        System.out.println(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k));
                                        pathways.get(pathwayFindValue).add(pathways.get(pathwayFindValue).get(j).getNeighbors().get(k));
                                        count++;
                                        System.out.println(pathways.get(0).get(pathways.get(0).size()-1).equalsIgnoreIndividualNeighbors(board.getBoardGraph().getVertexById(xHeroLoc, yHeroLoc)));
                                    }
                                }

                            }
                        }
                        /*for(int k = 0; k < pathways.get(i).get(j).getNeighbors().size(); k++)   {
                            if(!pathways.get(i).contains(pathways.get(i).get(j).getNeighbors().get(k))) {
                                if(pathways.get(i).get(j).getNeighbors().get(k).equalsIgnoreIndividualNeighbors(board.getBoardGraph().getVertexById(xMonsterLoc, yMonsterLoc)))   {
                                    //System.out.println(i);
                                    //System.out.println("This is the source");
                                }
                                else    {
                                   //System.out.println(pathways.get(i).get(j));
                                }
                            }
                        }*/
                    }
                    if(pathwayFindValue == pathways.size()-1)  {
                        System.out.println("No path was found so the monster will stay put");
                        monsterMovementFlag = false;
                    }
                }
                //pathways.get(0).add(board.getBoardGraph().getVertexById(xHeroLoc, yHeroLoc));
                System.out.println("There is no path yet");
            //}

            
            System.out.println("The order in which the monster must travel to the hero is:");
            //for(int i = 0; i < pathways.get(0).size(); i++) {
            //    System.out.println(pathways.get(0).get(i));
            //}
            //System.out.println(pathways.get(0).size()-1);
            //System.out.println(pathways.get(0).get(pathways.get(0).size()).getx());
            //System.out.println(pathways.get(0).get(pathways.get(0).size()).gety());
            //System.out.println(pathways.get(0).get(pathways.get(0).size()-1));
            //pathways.remove(0);
            /*for(int i = 0; i < pathways.size(); i ++)   {
                for(int j = 0; j < pathways.get(i).get(pathways.get(i).size()-1).getNeighbors().size(); j++)    {
                    if (board.getBoardGraph().getVertexById(pathways.get(i).get(pathways.get(i).size()-1).getNeighbors().get(j).getx(), pathways.get(i).get(pathways.get(i).size()-1).getNeighbors().get(j).gety()).getValue() instanceof AbstractCharacter)    {
                        System.out.println("Path " + i + " has a path to the hero");
                        System.out.println(pathways.get(i).get(pathways.get(i).size()-1));
                    }
                }
                /*if(pathways.get(i).size() != 0) {
                    if(pathways.get(i).get(pathways.get(i).size()-1).getNeighbors().size() == 2)    {
                        System.out.println("Two neighors at last path value");
                    }
                    if(pathways.get(i).get(pathways.get(i).size()-1).getNeighbors().size() == 3)    {
                        System.out.println("Three neighors at last path value");
                    }
                    if(pathways.get(i).get(pathways.get(i).size()-1).getNeighbors().size() == 4)    {
                        System.out.println("Four neighors at last path value");
                    }
                }
            }
            System.out.println("There are " + pathways.size() + "paths to the hero");
            */
            
            if(monsterMovementFlag)    {
                for(int i = 0; i < pathways.get(pathwayFindValue).size(); i++) {
                    System.out.println(pathways.get(pathwayFindValue).get(i));
                }
                System.out.println("We found the path!");
                board.getBoardGraph().getVertexById(xMonsterLoc, yMonsterLoc).setValue(new Tile());
                board.getBoardGraph().getVertexById(pathways.get(pathwayFindValue).get(pathways.get(pathwayFindValue).size()-1).getx(),pathways.get(pathwayFindValue).get(pathways.get(pathwayFindValue).size()-1).gety()).setValue(monster);
                monster.setXLoc(pathways.get(pathwayFindValue).get(pathways.get(pathwayFindValue).size()-1).getx());
                monster.setYLoc(pathways.get(pathwayFindValue).get(pathways.get(pathwayFindValue).size()-1).gety());
                view.update(xMonsterLoc,yMonsterLoc);
                view.update(pathways.get(pathwayFindValue).get(pathways.get(pathwayFindValue).size()-1).getx(),pathways.get(pathwayFindValue).get(pathways.get(pathwayFindValue).size()-1).gety());
            }
            pathwayFindValue = 0;

        }
    }
    public void setGameBoard(GameBoard view)    {
        this.view = view;
    }
    public static boolean searchForHero(AbstractCharacter hero)    {
        //System.out.println("Hero Size" + heroList.size());
        for(int i = 0; i < Tester.heroList.size(); i++)   {
            if(hero instanceof Barbarian)    {
                //System.out.println("You are a barbarian");
                if(Tester.heroList.get(i) instanceof Barbarian)    {
                    //System.out.println("We found the Barbarian!");
                    Tester.moveHeroValue = i;
                    return true;                       
                }
            }
            if(hero instanceof Dwarf)    {
                //System.out.println("You are a Dwarf");
                if(Tester.heroList.get(i) instanceof Dwarf)    {
                    //System.out.println("We found the Dwarf!");
                    Tester.moveHeroValue = i;
                    return true;                       
                }
            }
            if(hero instanceof Elf)    {
                //System.out.println("You are an Elf");
                if(Tester.heroList.get(i) instanceof Elf)    {
                    //System.out.println("We found the Elf!");
                    Tester.moveHeroValue = i;
                    return true;                       
                }
            }
            if(hero instanceof Wizard)    {
                //System.out.println("You are a Wizard");
                if(Tester.heroList.get(i) instanceof Wizard)    {
                    //System.out.println("We found the hero!");
                    Tester.moveHeroValue = i;
                    return true;                       
                }
            }
        }
        return false;
    }
    /*
    public void copyAllFields(Barbarian barbarian, GameBoard gameBoard,AbstractCharacter hero,AbstractMonster monster,TurnHandler th,int count, int x, int y, int diceRoll, int damageValue, int xMonst, int yMonst, ArrayList<AbstractCharacter> heroList, ArrayList<AbstractMonster> monsterList, ArrayList<AbstractMonster> targetMonsterList,int indexLoc, boolean flag,boolean targetFlag,int loopValue, int moveHeroValue)    {
        this.barbarian = barbarian;
        this.view = gameBoard;
        this.hero = hero;
        this.monster = monster;
        this.th = th;
        this.count = count;
        this.x = x;
        this.y = y;
        this.diceRoll = diceRoll;
        this.damageValue = damageValue;
        this.xMonst = xMonst;
        this.yMonst = yMonst;
    }*/
    public static void setupActions(JPanel main, GridBagConstraints c)    {   
        //JButton diceRollValue = new JButton();
        //JPanel diceRollValue = new JPanel();hero
        JButton turnDisplay = new JButton();
        JLabel turnLabel = new JLabel();
        //JLabel moveLabel = new JLabel();
        JButton damageRollValue = new JButton();
        JButton movementButton = new JButton("Move!");
        movementButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                
                //&& searchForHero(barbarian)
                if(th.getCurrentPlayer() == 0 )  {
                    if(!moveFlag  && searchForHero(barbarian))   {//System.out.println("One barbarian");
                        heroMovement(heroList.get(moveHeroValue), view);
                        moveLabel.setText("Movement Points: " + diceRoll);
                        moveFlag = true;
                    }
                    else    {
                        System.out.println("You've already moved");
                    }
                }
                if(th.getCurrentPlayer() == 1)  {
                    if(!moveFlag && searchForHero(dwarf))   {
                        heroMovement(heroList.get(moveHeroValue),view);
                        moveLabel.setText("Movement Points: " + diceRoll);
                        moveFlag = true;
                    }
                }
                if(th.getCurrentPlayer() == 2)  {
                    if(!moveFlag && searchForHero(elf))   {
                        heroMovement(heroList.get(moveHeroValue),view);
                        moveLabel.setText("Movement Points: " + diceRoll);
                        moveFlag = true;
                    }
                    
                }
                if(th.getCurrentPlayer() == 3)  {
                    if(!moveFlag && searchForHero(wizard))   {
                        heroMovement(heroList.get(moveHeroValue),view);
                        moveLabel.setText("Movement Points: " + diceRoll);
                        moveFlag = true;
                    }
                    
                }
                //System.out.println("Move!");
            }
        });
        JButton diceRollValue = new JButton();
        moveLabel.setText("Movement Points: " + diceRoll);
        moveLabel.setVerticalAlignment(SwingConstants.CENTER);
        moveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.ipadx = 40;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        /*c.weightx = 0;
        c.ipadx = 20;
        c.ipady = 20;*/ 
        c.ipady = 20;
        main.add(movementButton,c);
        c.gridx = 2;
        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        diceRollValue.add(moveLabel);
        c.ipadx = 0;
        c.ipady = 20;
        main.add(diceRollValue,c);
        JButton attackButton = new JButton("Attack");
        JLabel damageLabel = new JLabel();
        attackButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                if(th.getCurrentPlayer() == 0 && searchForHero(barbarian) )  {
                    if(!attackFlag) {
                        //System.out.println("One barbarian");
                        heroCombat(heroList.get(moveHeroValue), view);
                        checkWinCondition();
                        if(victoryFlag) {
                           JOptionPane.showMessageDialog (null, "You won by killing Vera the Gargoyle!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                        damageLabel.setText("Damage dealt: " + damageValue);
                        //view.update();
                        attackFlag = true;
                    }
                    else    {
                        System.out.println("You've already attacked");
                    }
                }
                if(th.getCurrentPlayer() == 1 && searchForHero(dwarf))  {
                    if(!attackFlag) {//System.out.println("One Dwarf");
                    heroCombat(heroList.get(moveHeroValue), view);
                    checkWinCondition();
                    if(victoryFlag) {
                        JOptionPane.showMessageDialog (null, "You won by killing Vera the Gargoyle!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    damageLabel.setText("Damage dealt: " + damageValue);
                    //view.update();
                    attackFlag = true;
                    }
                }
                if(th.getCurrentPlayer() == 2 && searchForHero(elf))  {
                    //System.out.println("One elf");
                    if(!attackFlag) {
                    heroCombat(heroList.get(moveHeroValue), view);
                    checkWinCondition();
                    if(victoryFlag) {
                           JOptionPane.showMessageDialog (null, "You won by killing Vera the Gargoyle!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    damageLabel.setText("Damage dealt: " + damageValue);
                    //view.update();
                    attackFlag = true;
                    }
                }
                if(th.getCurrentPlayer() == 3 && searchForHero(wizard))  {
                    //System.out.println("One wizard");
                    if(!attackFlag) {
                    heroCombat(heroList.get(moveHeroValue), view);
                    checkWinCondition();
                    if(victoryFlag) {
                        JOptionPane.showMessageDialog (null, "You won by killing Vera the Gargoyle!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    damageLabel.setText("Damage dealt: " + damageValue);
                    //view.update();
                    attackFlag = true;
                    }
                }
               //System.out.println("Attack!");
            }
        });
        c.ipadx = 35;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridx = 1;
        c.gridy = 2;
        c.ipady = 20;
       
        
        main.add(attackButton,c);
        c.gridx = 2;
        c.ipady = 20;
        c.ipadx = 20;
        damageLabel.setText("Damage dealt: " + damageValue);
        damageRollValue.add(damageLabel);
        main.add(damageRollValue,c);
        JButton endTurnButton = new JButton("End Turn");
        endTurnButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {               
               if(th.getCurrentPlayer() == 3)  {
                    doAllActiveMonsterActions();                              
                    th.switchSides(); 
                    if(checkLoseCondition())    {
                        JOptionPane.showMessageDialog (null, "You lost all your heroes and that means you lose!", "Loser!!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    //view.update();
                    //System.out.println("This is the player after 3 " + th.getCurrentPlayer());
                }
               else {
                   th.switchSides();
               }
               System.out.println("End Turn!");
               if(th.getCurrentPlayer() == 0)   {
                   turnLabel.setText("B's turn");
               }
               if(th.getCurrentPlayer() == 1)   {
                   turnLabel.setText("D's turn");
               }
               if(th.getCurrentPlayer() == 2)   {
                   turnLabel.setText("E's turn");
               }
               if(th.getCurrentPlayer() == 3)   {
                   turnLabel.setText("W's turn");
               }
               System.out.println("It is currently player " + th.getCurrentPlayer() + "'s turn");
               moveFlag = false;
               attackFlag = false;
            }
        });
        c.ipadx = 20;
        c.ipady = 20;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridx = 1;
        c.gridy = 3;
        main.add(endTurnButton,c);
        c.gridx = 2;
        if(th.getCurrentPlayer() == 0)   {
            turnLabel.setText("B's turn");
        }
        if(th.getCurrentPlayer() == 1)   {
            turnLabel.setText("D's turn");
        }
        if(th.getCurrentPlayer() == 2)   {
            turnLabel.setText("E's turn");
        }
        if(th.getCurrentPlayer() == 3)   {
            turnLabel.setText("W's turn");
        }
        c.ipadx = 75;
        turnDisplay.add(turnLabel);
        main.add(turnDisplay,c);

        //searchForHero(barbarian);
        if(heroList.size() == 0)    {
            JPanel barbTitlePanel = new JPanel();
            JLabel barbTitleLabel = new JLabel("Barbarian Stats");
            JPanel dwarfTitlePanel = new JPanel();
            JLabel dwarfTitleLabel = new JLabel("Dwarf Stats");
            JPanel elfTitlePanel = new JPanel();
            JLabel elfTitleLabel = new JLabel("Elf Stats");
            JPanel wizardTitlePanel = new JPanel();
            JLabel wizardTitleLabel = new JLabel("Wizard Stats");
            c.ipadx = 35;
            c.gridx = 3;
            c.gridy = 1;
            c.fill=GridBagConstraints.HORIZONTAL;
            barbTitlePanel.add(barbTitleLabel);
            
            JPanel barbHpPanel = new JPanel();
            JPanel barbAttackPanel = new JPanel();
            
            JPanel dwarfHpPanel = new JPanel();
            
            JPanel dwarfAttackPanel = new JPanel();
            JLabel dwarfAttackLabel = new JLabel();
            JPanel elfAttackPanel = new JPanel();
            
            JPanel elfHpPanel = new JPanel();
            
            JPanel wizardHpPanel = new JPanel();
            
            JPanel wizardAttackPanel = new JPanel();
            
            if(heroList.size() == 0)    {
                main.add(barbTitlePanel,c);
                barbHpLabel.setText("HP: " + 8);
                barbHpPanel.add(barbHpLabel);
                c.gridy = 2;
                main.add(barbHpPanel,c);

                barbAttackLabel.setText("Attack: " + 3);
                barbAttackPanel.add(barbAttackLabel);            
                c.gridy = 3;
                main.add(barbAttackPanel,c);
                c.gridx = 4;
                c.gridy = 1;
                dwarfTitlePanel.add(dwarfTitleLabel);
                main.add(dwarfTitlePanel,c);
                dwarfHpLabel.setText("HP: " + 7);
                dwarfHpPanel.add(dwarfHpLabel);
                c.gridy = 2;
                main.add(dwarfHpPanel,c);
                

                dwarfAttackLabel.setText("Attack: " + 2);
                dwarfAttackPanel.add(dwarfAttackLabel);
                 c.gridy = 3;
                main.add(dwarfAttackPanel,c);

                c.gridx = 5;
                c.gridy = 1;
                elfTitlePanel.add(elfTitleLabel);
                main.add(elfTitlePanel,c);
                

                elfHpLabel.setText("HP: " + 6);

                elfHpPanel.add(elfHpLabel);
                c.gridy = 2;
                main.add(elfHpPanel,c);
                

                elfAttackLabel.setText("Attack: " + 2);

                elfAttackPanel.add(elfAttackLabel);
                c.gridy = 3;
                main.add(elfAttackPanel,c);

                
                c.gridx = 6;
                c.gridy = 1;
                wizardTitlePanel.add(wizardTitleLabel);
                main.add(wizardTitlePanel,c);
                

                wizardHpLabel.setText("HP: " + 4);

                wizardHpPanel.add(wizardHpLabel);
                c.gridy = 2;
                main.add(wizardHpPanel,c);
                
                
                wizardAttackLabel.setText("Attack: " + 1);

                wizardAttackPanel.add(wizardAttackLabel);
                c.gridy = 3;
                main.add(wizardAttackPanel,c);
                 //NEED TO FIX GUI WHEN HERO DIES
            }
            
            
        }
        
        
    }
    public static void updateInfoPanel()   {
            
            if(heroList.size() == 4)    {
                barbHpLabel.setText("HP: " + heroList.get(0).getCurrentHitPoints());
                barbAttackLabel.setText("Attack: " + heroList.get(0).getWeaponPower());
                dwarfHpLabel.setText("HP: " + heroList.get(1).getCurrentHitPoints());                
                dwarfAttackLabel.setText("Attack: " + heroList.get(1).getWeaponPower());               
                elfHpLabel.setText("HP: " + heroList.get(2).getCurrentHitPoints());              
                elfAttackLabel.setText("Attack: " + heroList.get(2).getWeaponPower());
                wizardHpLabel.setText("HP: " + heroList.get(3).getCurrentHitPoints());                                
                wizardAttackLabel.setText("Attack: " + heroList.get(3).getWeaponPower());

            }
            if(heroList.size() == 3)    {
                for(int i = 0; i < heroList.size(); i ++)   {
                    if(heroList.get(i) instanceof Barbarian)    {
                        barbHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        barbAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                    if(heroList.get(i) instanceof Dwarf)    {
                        dwarfHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        dwarfAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                    if(heroList.get(i) instanceof Elf)    {

                        elfHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        elfAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                    if(heroList.get(i) instanceof Wizard)    {
                        wizardHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        wizardAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                }
            }
            if(heroList.size() == 2)    {
                for(int i = 0; i < heroList.size(); i ++)   {
                    if(heroList.get(i) instanceof Barbarian)    {
                        barbHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        barbAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                    if(heroList.get(i) instanceof Dwarf)    {
                        dwarfHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        dwarfAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                    if(heroList.get(i) instanceof Elf)    {
                        elfHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        elfAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                    if(heroList.get(i) instanceof Wizard)    {
                        wizardHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        wizardAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                }
            }
            if(heroList.size() == 1)    {
                for(int i = 0; i < heroList.size(); i ++)   {
                    if(heroList.get(i) instanceof Barbarian)    {
                        barbHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        barbAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                    if(heroList.get(i) instanceof Dwarf)    {
                        dwarfHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        dwarfAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                    if(heroList.get(i) instanceof Elf)    {
                        elfHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        elfAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                    if(heroList.get(i) instanceof Wizard)    {
                        wizardHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        wizardAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                }
            }
            if(heroList.size() == 0)   {
                for(int i = 0; i < heroList.size(); i ++)   {
                    if(heroList.get(i) instanceof Barbarian)    {
                        barbHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        barbAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                    if(heroList.get(i) instanceof Dwarf)    {
                        dwarfHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        dwarfAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                    if(heroList.get(i) instanceof Elf)    {
                        elfHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        elfAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                    if(heroList.get(i) instanceof Wizard)    {
                        wizardHpLabel.setText("HP: " + heroList.get(i).getCurrentHitPoints());
                        wizardAttackLabel.setText("Attack: " + heroList.get(i).getWeaponPower());
                    }
                }
            }
        
    }
    public static void doAllActiveMonsterActions() {
        for(int i = 0; i < monsterList.size(); i++) {
            if(monsterList.get(i).isActivated())    {
                //shortestPath(monsterList.get(i),view);
                monsterCombat(view,monsterList.get(i));
                System.out.println("Damage value " + damageValue);
            }
        }
    }
    public static int checkWinCondition() {
        for(int i = 0; i < monsterList.size(); i++) {
            if(monsterList.get(i) instanceof Gargoyle)  {
                victoryFlag = false;
                return 0;
            }
        }
        victoryFlag = true;
        return 0;
    }
    public static boolean checkLoseCondition() {
        if(heroList.size() == 0)    {
            return true;
        }
        else    {
            return false;
        }
    }
    public TurnHandler getTh()  {
        return this.th;
    }
    public GameBoard getView()  {
        return this.view;
    }
    public void addToHeroList(AbstractCharacter hero)   {
        this.heroList.add(hero);
    }
    public ArrayList getHeroList()  {
        return this.heroList;
    }
    public void addtoMonsterList(AbstractMonster monster)   {
        this.monsterList.add(monster);
    }
    public static void main(String[] args)  {
        TurnHandler th = new TurnHandler(0);
        GameBoard gameBoard = new GameBoard();
        //tester.getTh().setCurrentPlayer(1);
        
        //Should be 2 15
        Barbarian barbarian = new Barbarian();
        barbarian.setXLoc(2);
        barbarian.setYLoc(15);
        
        
        Elf elf = new Elf();
        elf.setXLoc(2);
        elf.setYLoc(14);
        
        Dwarf dwarf = new Dwarf();
        dwarf.setXLoc(1);
        dwarf.setYLoc(15);
        
        Wizard wizard = new Wizard();
        wizard.setXLoc(1);
        wizard.setYLoc(14);
       
        //This should be x = 3 y = 6
        //Spellbook Room Goblin
        AbstractMonster goblin = new Goblin();
        goblin.setXLoc(3);
        goblin.setYLoc(6);
       
        
        
        //Table Room Goblin (Left)
        AbstractMonster goblin2 = new Goblin();
        goblin2.setXLoc(6);
        goblin2.setYLoc(7);
        
        //Table Room Goblin (Right)
        AbstractMonster goblin3 = new Goblin();
        goblin3.setXLoc(7);
        goblin3.setYLoc(7);
        
        //Torture Room Goblin
        AbstractMonster goblin4 = new Goblin();
        goblin4.setXLoc(2);
        goblin4.setYLoc(11);
               
        //Weapon Rack Room Goblin
        AbstractMonster goblin5 = new Goblin();
        goblin5.setXLoc(10);
        goblin5.setYLoc(14);
        
        
        
        //This should be x = 4 y = 5
        //Spellbook Room Orc
        AbstractMonster orc = new Orc();
        orc.setXLoc(4);
        orc.setYLoc(5);
        
        //Torture rack Room Orc
        AbstractMonster orc2 = new Orc();
        orc2.setXLoc(2);
        orc2.setYLoc(12);
        
        //Writing desk Room Orc (Top Left)
        AbstractMonster orc3 = new Orc();
        orc3.setXLoc(7);
        orc3.setYLoc(14);
        
        //Writing desk Room Orc (Bottom Right)
        AbstractMonster orc4 = new Orc();
        orc4.setXLoc(8);
        orc4.setYLoc(15);
        
        //Boss Room Orc (Bottom Left)
        AbstractMonster orc5 = new Orc();
        orc5.setXLoc(11);
        orc5.setYLoc(11);
        
        //Boss Room Orc (Top Right)
        AbstractMonster orc6 = new Orc();
        orc6.setXLoc(14);
        orc6.setYLoc(8);
        
        //Empty Top left Room Skeleton (Left)
        AbstractMonster skeleton = new Skeleton();
        skeleton.setXLoc(2);
        skeleton.setYLoc(2);
        
        //Empty Top left Room Skeleton (Right)
        AbstractMonster skeleton2 = new Skeleton();
        skeleton2.setXLoc(3);
        skeleton2.setYLoc(2);
        
        //Tomb Room Skeleton (Top)
        AbstractMonster skeleton3 = new Skeleton();
        skeleton3.setXLoc(9);
        skeleton3.setYLoc(1);
        
        //Tomb Room Skeleton (Bottom)
        AbstractMonster skeleton4 = new Skeleton();
        skeleton4.setXLoc(9);
        skeleton4.setYLoc(3);
        
        //Empty Room with Mummy (Top)
        AbstractMonster zombie = new Zombie();
        zombie.setXLoc(6);
        zombie.setYLoc(1);
        
        //Empty Room with Mummy (Bottom)
        AbstractMonster zombie2 = new Zombie();
        zombie2.setXLoc(6);
        zombie2.setYLoc(3);
        
        //Empty Room with Zombies
        AbstractMonster mummy = new Mummy();
        mummy.setXLoc(6);
        mummy.setYLoc(2);
        
        AbstractMonster mummy2 = new Mummy();
        mummy2.setXLoc(9);
        mummy2.setYLoc(4);
        
        //Weapon Rack Room Fimir
        AbstractMonster fimir = new Fimir();
        fimir.setXLoc(10);
        fimir.setYLoc(16);
        
        //Boss Room Chaos Warrior
        AbstractMonster chaosWarrior = new ChaosWarrior();
        chaosWarrior.setXLoc(14);
        chaosWarrior.setYLoc(11);
        
        //Two shelf Room Chaos Warrior (Bottom Left)
        AbstractMonster chaosWarrior2 = new ChaosWarrior();
        chaosWarrior2.setXLoc(15);
        chaosWarrior2.setYLoc(16);
        
        //Two shelf Room Chaos Warrior (Top Right)
        AbstractMonster chaosWarrior3 = new ChaosWarrior();
        chaosWarrior3.setXLoc(16);
        chaosWarrior3.setYLoc(15);
        
        //Boss Room Gargoyle
        AbstractMonster gargoyle = new Gargoyle();
        gargoyle.setXLoc(12);
        gargoyle.setYLoc(8);
        
        
        
               
        gameBoard.getBoardGraph().getVertexById(3, 6).setValue(goblin);
        gameBoard.getBoardGraph().getVertexById(6, 7).setValue(goblin2);
        gameBoard.getBoardGraph().getVertexById(7, 7).setValue(goblin3);
        gameBoard.getBoardGraph().getVertexById(2, 11).setValue(goblin4);
        gameBoard.getBoardGraph().getVertexById(10, 14).setValue(goblin5);
        
        gameBoard.getBoardGraph().getVertexById(4, 5).setValue(orc);
        gameBoard.getBoardGraph().getVertexById(2, 12).setValue(orc2);
        gameBoard.getBoardGraph().getVertexById(7, 14).setValue(orc3);
        gameBoard.getBoardGraph().getVertexById(8, 15).setValue(orc4);
        gameBoard.getBoardGraph().getVertexById(11, 11).setValue(orc5);
        gameBoard.getBoardGraph().getVertexById(14, 8).setValue(orc6);
        
        gameBoard.getBoardGraph().getVertexById(2, 2).setValue(skeleton);
        gameBoard.getBoardGraph().getVertexById(3, 2).setValue(skeleton2);
        gameBoard.getBoardGraph().getVertexById(9, 1).setValue(skeleton3);
        gameBoard.getBoardGraph().getVertexById(9, 3).setValue(skeleton4);
        
        gameBoard.getBoardGraph().getVertexById(6, 1).setValue(zombie);
        gameBoard.getBoardGraph().getVertexById(6, 3).setValue(zombie2);
        
        gameBoard.getBoardGraph().getVertexById(6, 2).setValue(mummy);
        gameBoard.getBoardGraph().getVertexById(9, 4).setValue(mummy2);
        
        gameBoard.getBoardGraph().getVertexById(10, 16).setValue(fimir);
        
        gameBoard.getBoardGraph().getVertexById(14, 11).setValue(chaosWarrior);
        gameBoard.getBoardGraph().getVertexById(15, 16).setValue(chaosWarrior3);
        gameBoard.getBoardGraph().getVertexById(16, 15).setValue(chaosWarrior3);
        
        gameBoard.getBoardGraph().getVertexById(12, 8).setValue(gargoyle);
        
        gameBoard.getBoardGraph().getVertexById(2, 15).setValue(barbarian);
        gameBoard.getBoardGraph().getVertexById(1, 15).setValue(dwarf);
        gameBoard.getBoardGraph().getVertexById(2, 14).setValue(elf);
        gameBoard.getBoardGraph().getVertexById(1, 14).setValue(wizard);

        th.setCurrentPlayer(0);
        
        Tester tester = new Tester(gameBoard,th);
        tester.addtoMonsterList(goblin);
        tester.addtoMonsterList(goblin2);
        tester.addtoMonsterList(goblin3);
        tester.addtoMonsterList(goblin4);
        tester.addtoMonsterList(goblin5);
               
        tester.addtoMonsterList(orc);
        tester.addtoMonsterList(orc2);
        tester.addtoMonsterList(orc3);
        tester.addtoMonsterList(orc4);
        tester.addtoMonsterList(orc5);
        tester.addtoMonsterList(orc6);
        
        tester.addtoMonsterList(skeleton);
        tester.addtoMonsterList(skeleton2);
        tester.addtoMonsterList(skeleton3);
        tester.addtoMonsterList(skeleton4);
        
        tester.addtoMonsterList(zombie);
        tester.addtoMonsterList(zombie2);
        
        tester.addtoMonsterList(mummy);
        tester.addtoMonsterList(mummy2);
        
        tester.addtoMonsterList(fimir);
        
        tester.addtoMonsterList(chaosWarrior);
        tester.addtoMonsterList(chaosWarrior2);
        tester.addtoMonsterList(chaosWarrior3);
        
        tester.addtoMonsterList(gargoyle);
        
        tester.addToHeroList(barbarian);
        tester.addToHeroList(dwarf);
        tester.addToHeroList(elf);
        tester.addToHeroList(wizard);
        
    }
}
