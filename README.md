# SynapseMeasures_

This plugin was developed to try to quantify the accumulation of a protein (i.e. actin or clathrin) at the Immunological Synapse. The sofware allows to obtain a ratio of the fluorescence intensity observed at the cell-to-cell contact zone respect other zones (not at the contact) of one cell contacting to another. The sofware takes into account the fluorescence in the contact of cell A, the fluorescence in other zones of cell A, the fluorescence corresponding to cell B (the contacting cell), and the fuorescence apported by the background.To achive a correct installation and launching, you should download the one single JAR file [SynapseMeasures_.jar](https://github.com/anaacayuela/SynapseMeasures_/releases/download/1.0/SynapseMeasures_.jar) and place it into the "plugins" folder of ImageJ/Fiji. Note that for a better understanding of this plugin, you shall do the following steps:

When use Synapse Measures plugin, Please, cite this paper: Calabia-Linares C, et al. Endosomal clathrin drives actin accumulation at the immunological synapse. Journal of Cell Science 24(Pt 5):820-30. 2011.

## •STEP 1.
It is better to try to analyze the maximal projection of the image channel you select using ImageJ/Fiji -> ZProject... tool (ImageJ/Fiji -> Stacks -> ZProject... -> Max Intensity) althought this step is not essential, you can start analyzing whichever image type or bit depth you desire.

![stepf1](https://user-images.githubusercontent.com/54528366/65420797-55a9c100-de02-11e9-8da1-ebc9c0072071.png)
![step1](https://user-images.githubusercontent.com/54528366/65420841-6fe39f00-de02-11e9-8d45-4fb483803871.jpg)

## •STEP 2.
Select Synapse Measures plugin (ImageJ/Fiji ->Plugings -> Synapse Measures) and then click “Initialize” button.

![stepss1 1](https://user-images.githubusercontent.com/54528366/65420947-c355ed00-de02-11e9-99bd-cb1386c831ae.png)
![stepss1 1 1](https://user-images.githubusercontent.com/54528366/65421106-36f7fa00-de03-11e9-82e9-38db99b7eca5.png)

## •STEP 3.
At this point, you should select the counter type. It is recommended to start with “Type Bg” counter to mark the background by clicking with the mouse several times over the image background (outside cell structure).

![stepsss2](https://user-images.githubusercontent.com/54528366/65421200-758db480-de03-11e9-8095-f20655215e9a.png)
![droppedImage_2](https://user-images.githubusercontent.com/54528366/65421254-981fcd80-de03-11e9-957c-8c015f80b03a.jpg)

## •STEP 4. 
Click on “Type B” counter to mark the area in the cell B (not at the cell to cell contact site). (*)IMPORTANT: the circles must be half in the cell and half outside.

![stepss3](https://user-images.githubusercontent.com/54528366/65421324-bede0400-de03-11e9-957a-100fee287c70.png)
![droppedImage_4](https://user-images.githubusercontent.com/54528366/65421365-d1f0d400-de03-11e9-8d37-a4fe4b0f2b89.jpg)

## •STEP 5. 
Click on “Type Tsyn” counter to mark the area in cell to cell contact site.

![stepss4](https://user-images.githubusercontent.com/54528366/65421428-fc429180-de03-11e9-8983-810beef3f963.png)
![droppedImage_6](https://user-images.githubusercontent.com/54528366/65421453-0bc1da80-de04-11e9-906a-81b6b8bb6a2e.jpg)

## •STEP 6. 
Click on “Type Tnosyn” counter to mark the area in cell A (not in the  cell to cell contact site).

![stepss5](https://user-images.githubusercontent.com/54528366/65421533-3c097900-de04-11e9-9048-6bc138a5e02c.png)
![droppedImage_8](https://user-images.githubusercontent.com/54528366/65421556-4af02b80-de04-11e9-84a7-5c28eeb4db85.jpg)

## •STEP 7.
Click “Measure Cell” button. At this point, you will obtain the ratio of the fluorescence signal at the cell to cell contact site respect to the signal at other part in the cell A.

![stepssfinal](https://user-images.githubusercontent.com/54528366/65421705-af12ef80-de04-11e9-8db5-fb18357092f6.png)
![droppedImage_11 (1)](https://user-images.githubusercontent.com/54528366/65421731-c356ec80-de04-11e9-842f-fc641c00aa72.jpg)

## •STEP 8.
Repeat these previous steps as many measurements as you need. (The data will be saved by the program). Once finish, press on “Final Measure” button. The data corresponding to the “Ratio” from all measurements will appear in a new window called "Results" and they can be exported for further analysis.

Note: the size/area of the circles can be adjusted by using the “Larger” (bigger size) or “Smaller” (smaller size) buttoms. If you want to delete the last circle made, you should press on “Delete” button. Moreover, if you need to delete every circle made, you shall press on "Reset" button.
