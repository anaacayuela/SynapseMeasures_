/*
 * Synapse_Measures.java
 *
 * Created on February, 24 2009 on the basis of Cell_Counter
 *
 */
/*
 *
 * @author Kurt De Vos � 2005
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation (http://www.gnu.org/licenses/gpl.txt )
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA. 
 *
 */

import ij.plugin.frame.PlugInFrame;

/**
 *
 * @author Carlos Oscar S. Sorzano
 */
public class Synapse_Measures extends PlugInFrame{
    
    /** Creates a new instance of Synapse_Measures */
    public Synapse_Measures() {
         super("Synapse Measures");
         new SynapseMeasures();
    }
    
    public void run(String arg){
    }
  
}
