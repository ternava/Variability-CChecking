/* file : CheckJarvisMarchSedgewick.java
 * 
 * Project : Euclide
 *
 * ===========================================
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or (at
 * your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY, without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library. if not, write to :
 * The Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 * 
 * Created on 1 avr. 2007
 *
 */

package math.geom2d.point;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import math.geom2d.*;
import math.geom2d.line.LineSegment2D;

/**
 * Check computation of kd-tree using Jarvis March, on point set given in
 * R. Sedgewick's book.
 * @author dlegland
 *
 */
public class CheckKDTree2DSedgewick extends JPanel{

	private static final long serialVersionUID = 7331324136801936514L;
	
	ArrayList<Point2D> points;
	KDTree2D tree;

	
	public CheckKDTree2DSedgewick() {
		super();
		
		// Point coordinate are multiplied by 10 for better drawing
		points = new ArrayList<Point2D>(16);
		points.add(new Point2D(130, 190)); 
		points.add(new Point2D(210, 110)); 
		points.add(new Point2D(160, 180)); 
		points.add(new Point2D(140, 130)); 
		points.add(new Point2D(150, 250)); 
		points.add(new Point2D(180, 210)); 
		points.add(new Point2D(110, 160)); 
		points.add(new Point2D(170, 140)); 
		points.add(new Point2D(190, 170)); 
		points.add(new Point2D(240, 150)); 
		points.add(new Point2D(200, 230)); 
		points.add(new Point2D(260, 240)); 
		points.add(new Point2D(250, 120)); 
		points.add(new Point2D(230, 260)); 
		points.add(new Point2D(130, 220)); 
		points.add(new Point2D(220, 200));
		
		tree = new KDTree2D(points);
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		drawTree(g2, tree.getRoot(), 0, 100, 300, 100, 300);
	}

	private void drawTree(Graphics2D g2, KDTree2D.Node node, int step,
	        double xmin, double xmax, double ymin, double ymax) {
	    if(node==null)
	        return;
	    
	    int dir = step%2;
	    Point2D point = node.getPoint();
        double x = point.x();
        double y = point.y();
        
	    if(dir==0){
	        // Draw vertical line
	        g2.setColor(Color.BLUE);
            new LineSegment2D(x, ymin, x, ymax).draw(g2);
	        
	        // reduce x range for each sub tree
	        drawTree(g2, node.getLeftChild(), step+1, 
	                xmin, x, ymin, ymax);
	        drawTree(g2, node.getRightChild(), step+1, 
	                x, xmax, ymin, ymax);
	    } else {
	        // Draw horizontal line
	        g2.setColor(Color.BLUE);
	        new LineSegment2D(xmin, y, xmax, y).draw(g2);
	        
	        // reduce y range for each sub tree
	        drawTree(g2, node.getLeftChild(), step+1, 
	                xmin, xmax, ymin, y);
	        drawTree(g2, node.getRightChild(), step+1, 
	                xmin, xmax, y, ymax);
	    }
	    
	    g2.setColor(Color.BLACK);
        point.draw(g2, 2.5);
	}

	public final static void main(String[] args){
		System.out.println("Check kd-tree");
		
		JPanel panel = new CheckKDTree2DSedgewick();
		JFrame frame = new JFrame("KD Tree");
		frame.setContentPane(panel);
		frame.setSize(500, 400);
		frame.setVisible(true);
	}
}
