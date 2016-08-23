#Healthee

An app to help rural users to list the symptoms seen and generate preliminary advice. The data(symptoms and user info) would be sent to servers for processing and analysis. The users could then connect to specialists (Voice/Video calls). After the server processes the data from patients, the specialists could also connect to the patients directly. 

A call could be scheduled at a later time if a local nurse is needed for examination by the remote doctor.

With rural user as primary audience, picture-rich interface is used in the app, supported with speech and text as secondary communication mode. Users could input data with voice(instead of typing) in their local dialect(Hindi for now).

All this is designed to work in offline mode. However, if there is access to internet, the users could connect via Skype.

####Certain experiments have been made wrt UX 
1. Image driven design, to create a natural interface without any pre-requisites.
2. Text-to-speech and text in local language as secondary interface
3. Keeping in mind most users wouldn't be tech savvy, the elements are designmed bigger than normal
4. The (selection) buttons have been made large and moved to left to avoid accidental intraction during scroll.
5. Reducing the font and greying of in-active buttons dissuades users from using them. However, clickability has been retained, if the user intends to edit the data.

####Sequence:
1. User clicks to indicate he has trouble in specific part of the body. (Supported by Voice/Image)
2. Use is presented with a list of pre-loaded issues for that body part. (Image/Text/Voice)
3. User selects one or more from (2). Could also navigate back to (1) for additional choices.
4. Once done, the user clicks on the button and is taken to reccomendation page.
5. Here he is provided with a basic reco or actions which need to be immediately taken even before Doc is involved.
6. The user then registers his name and location and all this data is sent to the servers for analysis.
7. User can call a pre-configured number for more details.
8. The server will process and classify the nature of the problem and route it to the right specialists. !! This is manual for now !!
9. Specialists could then contact the patient for diagnosis. It could also be scheduled for a later date (at the nearest medical facility, where a nurse could help the doctor examine remotely). !! This is manual for now !!

<p align="center">
<img src="https://github.com/sumantics/Healthee/blob/master/page_1b.png" title="Select_organ" width="256">
<img src="https://github.com/sumantics/Healthee/blob/master/page_2.png" title="Select_problems" width="256">
<img src="https://github.com/sumantics/Healthee/blob/master/page_3.png" title="PreliminaryAdvice_inputName" width="256">
<img src="https://github.com/sumantics/Healthee/blob/master/page_4.png" title="Contact_CallCentreOrSpecialist" width="256">
<img src="https://github.com/sumantics/Healthee/blob/master/page_5.png" title="SkypeCall" width="256">
</p>
