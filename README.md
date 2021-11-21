# MANV-Triage - Sony SmartWatch 3

Mobile elektronische Datenerfassung bei einem Massenanfall von Verletzten (MANV).
Triage App für Bluetooth Kommunikation zw. Google Glass <> Wearable // -> MANV-Wearable

https://www.imis.uni-luebeck.de/de/forschung/projekte/manv


An augmented reality Triage application for mass casualty incidents on the Google Glass and Sony SmartWatch 3 with 
wireless data synchronization.

![watch](https://user-images.githubusercontent.com/12385737/142781878-282b3e7c-d397-400a-b619-af5fefdba597.png)

Mass casualty incidents are emergencies in which a high number of casualties or people who are affected in 
some other way occur at one time. Such an emergency represents a considerable challenge for the medical emergency services. 
It requires effective organization and coordination to ensure a smooth process in the treatment of patients. 
Part of this process is the so-called pre-triage. During this, it is the task of the first arriving emergency personnel to 
categorize the patients according to the severity of the injury and to transmit this information to the incident command center 
for mission planning. In practice, the rescue forces responsible for triage, are supported by an algorithm, which is run through 
and assigns a priority to the sighted patients.


![algo](https://user-images.githubusercontent.com/12385737/142781919-d6197321-1453-44da-8edc-1c59d5dec7fb.jpg)


Inputs and outputs are synchronized between devices. Likewise, a patient view summarizes persons already triaged according to 
classification and supports the process of patient data transfer. With an established network connection, this data is automatically 
transferred to the command and control center. Indicators at the top of the screen show the state of connection to the wireless network 
and to the Google Glass / Sony Smartwatch 3.


![start](https://user-images.githubusercontent.com/12385737/142781921-9c608f4e-aaf6-4ae4-b5c4-1a8d4682ebd8.png)


Project results:
- Connection of the existing application to the backing support server and implementation of data transfer.
- Mechanisms for data storage and delayed transmission.
- Synchronization of inputs and outputs in the Sony SmartWatch 3 and Google Glass application.
- Extension of the application with a screen displaying the number of casualties. 
- Extension of the applications with a second algorithm. In the form that more algorithms can be easily supplemented.

![transmit](https://user-images.githubusercontent.com/12385737/142781922-3e25e3bc-ffaa-41f5-989b-80727bbc2d6c.png)
