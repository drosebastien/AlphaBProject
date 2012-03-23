package gui;

import tree.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import java.util.ArrayList;

public class TreePanelTest2 extends TreePanel {
    private ArrayList<JNodePosition> list;

    public TreePanelTest2() {
        super();
        list = new ArrayList<JNodePosition>();

        addMouseListener(new MouseAdapter () {
            public void mouseClicked(MouseEvent e) {
                mouseClickedEvent(e);
            }
        });

        setMinimumSize(new Dimension(500, 500));
        setPreferredSize(new Dimension(700, 800));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //**
        TreeNode root = Tree.makeTree(8, 4);
        int[] tab = {1, 0};
        root.setLabel("[0, 1]");
        root.setType(NodeType.ANCESTOR_OF_CURRENT);
        ArrayList<Integer> pos = new ArrayList<Integer>();
        for(int i = 0; i < tab.length; i++) {
            for(int j = 0; j <= i; j++) {
                pos.add(tab[j]);
            }
            if(i < tab.length - 1) {
                    TreeNode node = Tree.getNode(pos, root);
                    node.setLabel("["+ (i + 1) + ", " + (i + 2) + "]");
                    node.setType(NodeType.ANCESTOR_OF_CURRENT);
            }
            else {
                TreeNode node = Tree.getNode(pos, root);
                node.setLabel("" + i);
                node.setType(NodeType.CURRENT);
            }
        }
        //*/TreeNode root = getTreeTest();

        list = new ArrayList<JNodePosition>();
        int rightMargin = JTree.drawTree(50, root, 40, 8, g, list);

        setPreferredSize(new Dimension(rightMargin, 800));
    }

    //arbre de tests
    public TreeNode getTreeTest() {
        TreeNode root = new TreeNode(null);
        //ajout du niv1
        TreeNode lvl1_1 = new TreeNode(root);
        TreeNode lvl1_2 = new TreeNode(root);
        lvl1_2.setLabel("[1, -]");
        TreeNode lvl1_3 = new TreeNode(root);
        root.addChildNode(lvl1_1);
        root.addChildNode(lvl1_2);
        root.addChildNode(lvl1_3);
        //ajout du niv2
        TreeNode lvl2_1 = new LeafNode(lvl1_1, 1);
        TreeNode lvl2_2 = new LeafNode(lvl1_1, 1);
        TreeNode lvl2_3 = new LeafNode(lvl1_1, 1);
        TreeNode lvl2_4 = new TreeNode(lvl1_2);
        lvl2_4.setLabel("[3, 4]");
        TreeNode lvl2_5 = new LeafNode(lvl1_3, 1);
        TreeNode lvl2_6 = new LeafNode(lvl1_3, 1);
        TreeNode lvl2_7 = new LeafNode(lvl1_2, 1);
        lvl1_1.addChildNode(lvl2_1);
        lvl1_1.addChildNode(lvl2_2);
        lvl1_1.addChildNode(lvl2_3);
        lvl1_2.addChildNode(lvl2_4);
        lvl1_2.addChildNode(lvl2_7);
        lvl1_3.addChildNode(lvl2_5);
        lvl1_3.addChildNode(lvl2_6);
        //ajout du niv3
        TreeNode lvl3_1 = new LeafNode(lvl2_4, 3);
        TreeNode lvl3_2 = new TreeNode(lvl2_4);
        lvl3_2.setLabel("[2, 3]");
        TreeNode lvl3_3 = new LeafNode(lvl2_4, 3);
        lvl2_4.addChildNode(lvl3_1);
        lvl2_4.addChildNode(lvl3_2);
        lvl2_4.addChildNode(lvl3_3);
        //ajout du niv4
        TreeNode lvl4_1 = new LeafNode(lvl3_2, 4);
        TreeNode lvl4_2 = new LeafNode(lvl3_2, 5);
        TreeNode lvl4_3 = new LeafNode(lvl3_2, 6);
        TreeNode lvl4_4 = new LeafNode(lvl3_2, 7);
        TreeNode lvl4_5 = new LeafNode(lvl3_2, 8);
        lvl4_5.setLabel("123");
        lvl3_2.addChildNode(lvl4_1);
        lvl3_2.addChildNode(lvl4_2);
        lvl3_2.addChildNode(lvl4_3);
        lvl3_2.addChildNode(lvl4_4);
        lvl3_2.addChildNode(lvl4_5);

        return root;
    }

    public void mouseClickedEvent(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //System.out.println("x : " + x + "; y : " + y);
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).isIn(x - 5, y - 5, x + 5, y + 5)) {
                //System.out.println(list.get(i));
                controller.clickOnNode(isInExplorerMode(),
                                       list.get(i).getTreePos());
            }
        }
    }

    public void nextEvent() {
    }

    public void previousEvent() {
    }
}
