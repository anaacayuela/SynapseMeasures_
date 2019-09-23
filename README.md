# SynapseMeasures_

This plugin was developed to try to quantify the accumulation of a protein (i.e. actin or clathrin) at the Immunological Synapse. The sofware allows to obtain a ratio of the fluorescence intensity observed at the cell-to-cell contact zone respect other zones (not at the contact) of one cell contacting to another. The sofware takes into account the fluorescence in the contact of cell A, the fluorescence in other zones of cell A, the fluorescence corresponding to cell B (the contacting cell), and the fuorescence apported by the background.To achive a correct installation and launching, you should download the one single JAR file [SynapseMeasures_.jar](https://github.com/anaacayuela/SynapseMeasures_/releases/download/1.0/SynapseMeasures_.jar) and place it into the "plugins" folder of ImageJ/Fiji. Note that for a better understanding of this plugin, you shall do the following steps:

When use Synapse Measures plugin, Please, cite this paper: Calabia-Linares C, et al. Endosomal clathrin drives actin accumulation at the immunological synapse. Journal of Cell Science 24(Pt 5):820-30. 2011.

•STEP 1. It is better to try to analyze the maximal projection of the image channel you select using ImageJ/Fiji -> ZProject... tool (ImageJ/Fiji -> Stacks -> ZProject... -> Max Intensity) althought this step is not essential, you can start analyzing whichever image type or bit depth you desire.

•STEP 2. Select Synapse Measures plugin (ImageJ/Fiji ->Plugings -> Synapse Measures) and then click “Initialize” button.

•STEP 3. At this point, you should select the counter type. It is recommended to start with “Type Bg” counter to mark the background by clicking with the mouse several times over the image background (outside cell structure).

•STEP 4. Click on “Type B” counter to mark the area in the cell B (not at the cell to cell contact site). (*)IMPORTANT: the circles must be half in the cell and half outside.

•STEP 5. Click on “Type Tsyn” counter to mark the area in cell to cell contact site.

•STEP 6. Click on “Type Tnosyn” counter to mark the area in cell A (not in the  cell to cell contact site).

•STEP 7. Click “Measure Cell” button. At this point, you will obtain the ratio of the fluorescence signal at the cell to cell contact site respect to the signal at other part in the cell A.

•STEP 8. Repeat these previous steps as many measurements as you need. (The data will be saved by the program). Once finish, press on “Final Measure” button. The data corresponding to the “Ratio” from all measurements will appear in a new window called "Results" and they can be exported for further analysis.

Note: the size/area of the circles can be adjusted by using the “Larger” (bigger size) or “Smaller” (smaller size) buttoms. If you want to delete the last circle made, you should press on “Delete” button. Moreover, if you need to delete every circle made, you shall press on "Reset" button.
