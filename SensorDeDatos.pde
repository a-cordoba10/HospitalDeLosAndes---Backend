 #include <string.h>
 // ID del usuario del dispositivo.
 int idUser = 1;
 // variables.
 int BPS = 120;
 int BPD = 80;
 int HR = 60;
 int STR = 69;
 int cont = 0;
 
 // Unidades de medida.
 String BPSUnit = "mmHG";
 String BPDUnit = "mmHG";
 String HRUnit = "beat/min";
 String STRUnit = "stress";
 
 // arreglo de chars para envio final del dato del sensor.
 String tempArray[4] = {"", "", "", ""};
 // variable temporal de conteo
 int i = 0;
 int j = 0;
 // preparacion del proceso
 void setup() { 
   // Abre puerto serial y lo configura a 9600 bps
     Serial.begin(9600);
     // se fijan los datos exactos.
     tempArray[0] = String(idUser);
 }
  int data =0;
  int medida = 0;
  int pulso = 0;
  int topeArray[500]={0};
  int topeIt = 0;
  int maximoNew=0;
  int maximoAnt=0;
  int periodo=0;
 void loop() {
   medida = analogRead(24);
   pulso = analogRead(25);
   for (i=0 ; i<499;i++)
   {
     topeArray[i]=topeArray[i+1];
   }
     topeArray[499]=medida;
   
   if(topeArray[250]>topeArray[0]&& topeArray[250]>topeArray[499]&&topeArray[250]>250){
     maximoAnt=maximoNew;
     maximoNew=topeArray[1];
     if (topeIt > 20){
       periodo=topeIt;
     }
     topeIt = 0;
   }
//   Serial.println(String(periodo));
   if (pulso < 100){
//     heartRate=(1/periodo)*60000;
       HR = periodo;
       envioData();
   }
//   Serial.println(String((medida*5)/1023)+"\t"+ String(data*(0.05)));
     
//     Serial.println(String(medida)+"\t"+ String(1023)+"\t"+ String(0)+"\t"+ String(pulso));
//   Serial.println("");
     data ++;
     topeIt ++;
//   delay(50);
     if (data == 1000){
       envioData();
       data=0;
     }
 }
 
 void envioEmergencia(String info) {
   tempArray[1] = String("EMERGENCIA");
   tempArray[2] = String(info);
   tempArray[3] = String("EMERGENCIA");
    for (j=0; j<4; j++){
     // imprime el elemento del arreglo por el puerto serial
     Serial.print(tempArray[j]);
     // espacio para diferenciar elementos en el arreglo
     if (j < 3){
       Serial.print("\t");      
     }
   }
   // final de la trama de datos
   Serial.println("");
   
 }
 
 // ejecuta el procesamiento del sensor
 void envioData() {
   
   for(i=0;i<4;i++){
       if (i==0){
         tempArray[1] = String("BPS");
         tempArray[2] = String(BPS);
         tempArray[3] = String(BPSUnit);       
       }
       if (i==1){
         tempArray[1] = String("BPD");
         tempArray[2] = String(BPD);
         tempArray[3] = String(BPDUnit);       
       }
       if (i==2){
         tempArray[1] = String("HR");
         tempArray[2] = String(HR);
         tempArray[3] = String(HRUnit);       
       }
       if (i==3){
         tempArray[1] = String("STR");
         tempArray[2] = String(STR);
         tempArray[3] = String(STRUnit);       
       }
       
     // Envia los datos uno por uno del arreglo del sensor por puerto serial
     for (j=0; j<4; j++){
       // imprime el elemento del arreglo por el puerto serial
       Serial.print(tempArray[j]);
       // espacio para diferenciar elementos en el arreglo
       if (j < 3){
         Serial.print("\t");      
       }
     }
     // final de la trama de datos
     Serial.println("");
   }
    i=0;
 }

