#include "Pass2.h"
#include"Instruction.h"
#include"Check.h"
#include<bits/stdc++.h>
#include<regex>
#include <stdlib.h>     /* atoi */

using namespace std;
Pass2::Pass2(vector<Instruction> instructionList,map<string,string>x)
{
    opTableFormat2=readOpTable.getOpTableFormat2();
    opTableFormat3=readOpTable.getOpTableFormat3();
    registers["A"]="0";
    registers["X"]="1";
    registers["L"]="2";
    registers["B"]="3";
    registers["S"]="4";
    registers["T"]="5";
    registers["F"]="6";
    symbolTable=x;
    validateInstruction(instructionList);



}


Pass2::~Pass2()
{
    //dtor
}

 void Pass2::validateInstruction(vector<Instruction> instructionList){


    for(int i=0;i<instructionList.size();i++){
        newInstruction=Instruction();
        newInstruction=instructionList[i];
     //   cout << newInstruction.getOperand() << endl;
        if(newInstruction.getOperator() == "BASE"){
        base = true;
        }else if (newInstruction.getOperator() == "NOBASE"){
        base = false;
        }
        if(newInstruction.getFormat()==2&&check.directives.count(newInstruction.getOperator())==0){
            checkFormat2();
            instructionList[i]=newInstruction;
        }else if (newInstruction.getFormat()==3&&check.directives.count(newInstruction.getOperator())==0){
            if (newInstruction.getOperator() == "LDB"){
                baseRelative = newInstruction.getOperand();
            }
            checkFormat3();
            instructionList[i]=newInstruction;
        }else if ((newInstruction.getFormat()==4&&check.directives.count(newInstruction.getOperator())==0)){
            checkFormat4();
            instructionList[i]=newInstruction;
        }

    }


 }
void Pass2::checkFormat2(){

    if(newInstruction.getOperator()=="CLEAR"||newInstruction.getOperator()=="TIXR"){
          string temp=readOpTable.opTableFormat2[newInstruction.getOperator()];
          string registerCheck=registers[newInstruction.getOperand()];
          if(registerCheck==""){
            newInstruction.setError("NOT VALID OPERAND");
          }else{
            temp+=registerCheck;
            temp+="0";
            newInstruction.setObjectCode(temp);
          }
    }else{
        string tempOperand=newInstruction.getOperand();
        if(std::count( tempOperand.begin(), tempOperand.end(), ',')==1){
            if(tempOperand.size()==3){
               string  registerCheck1=registers[tempOperand.substr(0,1)];
               string registerCheck2=registers[tempOperand.substr(2,2)];
                if(registerCheck1==""||registerCheck2==""){
                    newInstruction.setError("NOT VALID OPERAND");
                }else{
                    string temp=readOpTable.opTableFormat2[newInstruction.getOperator()];
                    temp+=registerCheck1;
                    temp+=registerCheck2;
                    newInstruction.setObjectCode(temp);
                }
            }else{
                newInstruction.setError("NOT VALID OPERAND");
            }

        }
    }
}

void Pass2::checkFormat3(){
    regex add = regex( "^0$|^[1-9][0-9]*$");
    regex e1= regex( "^#[0-9]*");
    regex e2= regex( "^#[A-Z0-9_]*");
    regex e3= regex( "^@[0-9]*");
    regex e4= regex( "^@[A-Z0-9_]*");
    regex e5=regex("^[A-Z0-9_]*");
    regex e6=regex("^[0-9]*,X");
    regex e7=regex ("^[A-Z0-9_]*,X");
    string tempOperand=newInstruction.getOperand();
    if(regex_match( tempOperand , add )){
        string s = newInstruction.getAddress();
        string temp = getInstructionAddress(s ,tempOperand , 0 , 3,false);
        if (temp == ""){
            if (base == true){
                temp = getInstructionAddress(baseRelative ,tempOperand , 1 , 0,false);
                if(temp == ""){
                    newInstruction.setError("DISPLACEMENT OUT OF RANGE");
                }else{
                    string nixbpe = "110100";
                    string newObjectCode = concatinateInstruction(readOpTable.opTableFormat3[newInstruction.getOperator()], nixbpe , temp);
                    newInstruction.setObjectCode(newObjectCode);
                    cout << newObjectCode << endl;
                }
            }else{
                newInstruction.setError("DISPLACEMENT OUT OF RANGE");
            }
        }else {
            string nixbpe = "110010";
            string newObjectCode = concatinateInstruction(readOpTable.opTableFormat3[newInstruction.getOperator()]
            , nixbpe , temp);
            newInstruction.setObjectCode(newObjectCode);
            cout << newObjectCode << endl;
        }
    //In case of #
    }else if(regex_match( tempOperand , e1 )||regex_match( tempOperand , e2 )){

        string operand=newInstruction.getOperand();
        //IN case of #1000
        if(regex_match( operand , e1 )){
            string nixbpe="010000";
            string address=operand.substr(1,operand.size());
            std::stringstream sstream;
            sstream << std::hex <<  atoi (address.c_str());
            address= sstream.str();
            switch(address.size()){
            case 1 : address="00"+address;
                    break;
            case 2 : address = "0"+address;
                    break;
            }
            if(address.size()>3){
                newInstruction.setError("OPERAND OUT OF RANGE");
            }else{
                string newObjectCode = concatinateInstruction(readOpTable.opTableFormat3[newInstruction.getOperator()], nixbpe ,address);
                newInstruction.setObjectCode(newObjectCode);
                cout<<newObjectCode<<endl;
            }
        //IN CASE OF #ADSADS
        }else if (regex_match( operand , e2 )){

            string operand=newInstruction.getOperand();
            operand=operand.substr(1,operand.size());
            string address=symbolTable[operand];

            if(address!=""){
                string location=newInstruction.getAddress();
                string temp = getInstructionAddress(location ,address , 0 , 3,true);
                 if (temp == ""){
                    if (base == true){//BASE RELATIVE
                        temp = getInstructionAddress(baseRelative ,address , 1 , 0,true);
                        if(temp == ""){
                            newInstruction.setError("DISPLACEMENT OUT OF RANGE");
                        }else{
                            string nixbpe = "010100";
                            string newObjectCode = concatinateInstruction(readOpTable.opTableFormat3[newInstruction.getOperator()], nixbpe , temp);
                            newInstruction.setObjectCode(newObjectCode);
                            cout << newObjectCode << endl;
                        }
                    }else{
                        newInstruction.setError("DISPLACEMENT OUT OF RANGE");
                    }

                }else{//PC RELATIVE
                    string nixbpe = "010010";
                    string newObjectCode = concatinateInstruction(readOpTable.opTableFormat3[newInstruction.getOperator()]
                    , nixbpe , temp);
                    newInstruction.setObjectCode(newObjectCode);
                    cout << newObjectCode << endl;
                }
            }else{
                newInstruction.setError("NOT VALID OPERAND");
            }
        }
        //IN CASE OF @
    }else if (regex_match( tempOperand , e3 )||regex_match( tempOperand , e4 )){
        string operand=tempOperand.substr(1,tempOperand.size());
        string address;
        bool isHex;
        if(regex_match( tempOperand , e3 )){
            address=operand;    //@1000
            isHex=false;
        }else{
            address=symbolTable[operand];   //@ASDASD
            isHex=true;
        }
        if(address!=""){        //ADDRESS OR FOUND IN SYMBOL TABLE
            string location=newInstruction.getAddress();
            string temp = getInstructionAddress(location ,address , 0 , 3,isHex);
            if (temp == ""){
                if (base == true){//BASE RELATIVE
                    temp = getInstructionAddress(baseRelative ,address , 1 , 0,isHex);
                    if(temp == ""){
                        newInstruction.setError("DISPLACEMENT OUT OF RANGE");
                    }else{
                        string nixbpe = "100100";
                        string newObjectCode = concatinateInstruction(readOpTable.opTableFormat3[newInstruction.getOperator()], nixbpe , temp);
                        newInstruction.setObjectCode(newObjectCode);
                        cout << newObjectCode << endl;
                    }
                }else{
                    newInstruction.setError("DISPLACEMENT OUT OF RANGE");
                }

            }else{//PC RELATIVE
                string nixbpe = "100010";
                string newObjectCode = concatinateInstruction(readOpTable.opTableFormat3[newInstruction.getOperator()]
                , nixbpe , temp);
                newInstruction.setObjectCode(newObjectCode);
                cout << newObjectCode << endl;
            }

        }else{
            newInstruction.setError("NOT VALID OPERAND");

        }


    }else if (regex_match( tempOperand , e5 )){
        string address=symbolTable[tempOperand];
        if(address!=""){
            string location=newInstruction.getAddress();
                string temp = getInstructionAddress(location ,address , 0 , 3,true);
                 if (temp == ""){
                    if (base == true){//BASE RELATIVE
                        temp = getInstructionAddress(baseRelative ,address , 1 , 0,true);
                        if(temp == ""){
                            newInstruction.setError("DISPLACEMENT OUT OF RANGE");
                        }else{
                            string nixbpe = "110100";
                            string newObjectCode = concatinateInstruction(readOpTable.opTableFormat3[newInstruction.getOperator()], nixbpe , temp);
                            newInstruction.setObjectCode(newObjectCode);
                            cout << "base  "<<newObjectCode << endl;
                        }
                    }else{
                        newInstruction.setError("DISPLACEMENT OUT OF RANGE");
                    }

                }else{//PC RELATIVE
                    string nixbpe = "110010";
                    string newObjectCode = concatinateInstruction(readOpTable.opTableFormat3[newInstruction.getOperator()]
                    , nixbpe , temp);
                    newInstruction.setObjectCode(newObjectCode);
                    cout << newObjectCode << endl;
                }

        }else{
            newInstruction.setError("NOT VALID OPERAND");
        }

    }else if (regex_match( tempOperand , e6 )||regex_match( tempOperand , e7 )){
        if(regex_match( tempOperand , e6 )){    //1000,x
            string operand=tempOperand.substr(0,tempOperand.size()-2);
            string location = newInstruction.getAddress();
            string temp = getInstructionAddress(location ,operand , 0 , 3,false);
            if (temp == ""){
                if (base == true){
                    temp = getInstructionAddress(baseRelative ,tempOperand , 1 , 0,false);
                    if(temp == ""){
                        newInstruction.setError("DISPLACEMENT OUT OF RANGE");
                    }else{
                        string nixbpe = "111100";
                        string newObjectCode = concatinateInstruction(readOpTable.opTableFormat3[newInstruction.getOperator()], nixbpe , temp);
                        newInstruction.setObjectCode(newObjectCode);
                    }
                }else{
                    newInstruction.setError("DISPLACEMENT OUT OF RANGE");
                }
            }else {
                string nixbpe = "111010";
                string newObjectCode = concatinateInstruction(readOpTable.opTableFormat3[newInstruction.getOperator()], nixbpe , temp);
                newInstruction.setObjectCode(newObjectCode);
            }
        }else{    // hamada ,x
            string operand=tempOperand.substr(0,tempOperand.size()-2);
            string address=symbolTable[operand];
            if(address!=""){
                string location = newInstruction.getAddress();
                string temp = getInstructionAddress(location ,address , 0 , 3,true);
                if (temp == ""){
                    if (base == true){
                        temp = getInstructionAddress(baseRelative ,address , 1 , 0,true);
                        if(temp == ""){
                            newInstruction.setError("DISPLACEMENT OUT OF RANGE");
                        }else{
                            string nixbpe = "111100";
                            string newObjectCode = concatinateInstruction(readOpTable.opTableFormat3[newInstruction.getOperator()], nixbpe , temp);
                            newInstruction.setObjectCode(newObjectCode);
                        }
                    }else{
                        newInstruction.setError("DISPLACEMENT OUT OF RANGE");
                    }
                }else {
                    string nixbpe = "111010";
                    string newObjectCode = concatinateInstruction(readOpTable.opTableFormat3[newInstruction.getOperator()], nixbpe , temp);
                    newInstruction.setObjectCode(newObjectCode);
                }


            }else{
                cout<<"msh fel symbol table"<<endl;
                newInstruction.setError("INVALID OPERAND");

            }
        }
    }else{

        cout<<"INVALID OPERAND"<<endl;
        newInstruction.setError("INVALID OPERAND");

    }
}

void Pass2::checkFormat4(){
    regex e1 = regex( "^[0-9]*");
    regex e2 = regex( "^[A-Z0-9_]*");
    regex e3 = regex( "^#[0-9]*");
    regex e4 = regex( "^#[A-Z0-9_]*");
    regex e5 = regex( "^[0-9]*\\,[xX]?");
    regex e6 = regex( "^[A-Z0-9_]*\\,[xX]?");
    regex e7 = regex( "^@[0-9]*");
    regex e8 = regex( "^@[A-Z0-9_]*");
    string temp_operand=newInstruction.getOperand();
    string nixbpe = "", operand = "", address = "";
    /**
        110001    +op  1000    address = HEX(1000)
    */
    if(regex_match( temp_operand , e1 )){
        //cout << "110001    +op  1000    address = HEX(1000)" << endl;
        operand=newInstruction.getOperand();
        address=operand;
        address= dec_to_hex(address);
        nixbpe="110001";
        string opj_code = create_opject_code(nixbpe,address);
        cout << opj_code << endl;
    }
    /**
        110001    +op  label    address = Label_address
    */
    else if(regex_match( temp_operand , e2 )){
        //cout << "110001    +op  label    address = Label_address" << endl;
        operand=newInstruction.getOperand();
        address=symbolTable[operand];
        nixbpe="110001";
        string opj_code = create_opject_code(nixbpe,address);
        cout << opj_code << endl;
    }
    /**
        010001    +op  #4000    address =  HEX(4000)
    */
    else if(regex_match( temp_operand , e3 )){
        operand=newInstruction.getOperand();
        address=operand.substr(1,operand.size());
        address= dec_to_hex(address);
        nixbpe="010001";
        string opj_code = create_opject_code(nixbpe,address);
        cout << opj_code << endl;

    }
    /**
        010001    +op  #label    address = Label_address
    */
    else if(regex_match( temp_operand , e4)){
        operand=newInstruction.getOperand();
        operand=operand.substr(1,operand.size());
        address=symbolTable[operand];
        nixbpe="010001";
        string opj_code = create_opject_code(nixbpe,address);
        cout << opj_code << endl;
    }
    /**
        111001    +op   1000,x    address = HEX(1000) + x
    */
    else if(regex_match( temp_operand , e5)){
        operand=newInstruction.getOperand();
        operand=operand.substr(0,operand.size()-2);
        address=operand;
        address= dec_to_hex(address);
        nixbpe="111001";
        string opj_code = create_opject_code(nixbpe,address);
        cout << opj_code << endl;
    }
    /**
        111001    +op   label,x    address = label_address + x
    */
    else if(regex_match( temp_operand , e6)){
        operand=newInstruction.getOperand();
        operand=operand.substr(0,operand.size()-2);
        address=symbolTable[operand];
        nixbpe="111001";
        string opj_code = create_opject_code(nixbpe,address);
        cout << opj_code << endl;
    }
    /**
        100001    +op   /@1000    address = ??
    */
    else if(regex_match( temp_operand , e7)){
        operand=newInstruction.getOperand();
        address=operand.substr(1,operand.size());
        address= dec_to_hex(address);
        nixbpe="100001";
        string opj_code = create_opject_code(nixbpe,address);
        cout << opj_code << endl;
    }
    /**
        100001    +op   /@label    address = ??
    */
    else if(regex_match( temp_operand , e8)){
        operand=newInstruction.getOperand();
        operand=operand.substr(1,operand.size());
        address=symbolTable[operand];
        nixbpe="100001";
        string opj_code = create_opject_code(nixbpe,address);
        cout << opj_code << endl;
    }
    else{
        newInstruction.setError("INVALID OPERAND");
    }

}

string Pass2::dec_to_hex(string str){
    std::stringstream sstream;
    sstream << std::hex <<  atoi (str.c_str());
    return sstream.str();
}

string Pass2::create_opject_code(string nixbpe, string address){
    switch(address.size()){
    case 1 : address="0000"+address;
        break;
    case 2 : address = "000"+address;
        break;
    case 3 : address = "00"+address;
        break;
    case 4 : address = "0"+address;
        break;
    }
    if(address.size() > 5){
        newInstruction.setError("OPERAND OUT OF RANGE");
    }else{
        string op = newInstruction.getOperator();
        op = op.substr(1,op.size());
        string new_object_code = concatinateInstruction(readOpTable.opTableFormat3[op], nixbpe , address);
        if(new_object_code.size()==6){new_object_code="00"+new_object_code;}
        else if(new_object_code.size()==7){new_object_code="0"+new_object_code;}
        newInstruction.setObjectCode(new_object_code);
        return new_object_code;
    }
}


string Pass2::getInstructionAddress( string pc , string add , int flag, int format,bool isHex){
    int n;
    int m;
    if(isHex){
         std::istringstream(add) >> std::hex >> m;
    }else{
        m = atoi (add.c_str());
    }
    std::stringstream sstream;
    std::istringstream(pc) >> std::hex >> n;
    sstream << std::hex <<  m - (n+format);
    if(flag==0 &&(m-(n+format))>=-2048 && (m-(n+format)) <= 2047){
        string result = sstream.str();
        switch(result.size()){
            case 1 : result="00"+result;
                    break;
            case 2 : result = "0"+result;
                    break;
        }
        result = result.substr(result.size()-3 , result.size());
        return result;
    }else if(flag==1 &&(m-(n+format))>=0 && (m-(n+format)) <= 4096){
        string result = sstream.str();
        switch(result.size()){
            case 1 : result="00"+result;
                    break;
            case 2 : result = "0"+result;
                    break;
        }
        result = result.substr(result.size()-3 , result.size());
        return result;
    }
    string result = "";
    return result;
}


string Pass2::concatinateInstruction(string opCode , string nixbpe , string address){
        opCode = "0x"+opCode;
    stringstream ss;
    ss << hex << opCode;
    unsigned n;
    ss >> n;
    bitset<8> b(n);
   string result = b.to_string() ;
   result = result.substr(0,6);
   result += nixbpe;
    unsigned long long value = std::bitset<64>(result).to_ullong();
    std::stringstream sstream;
    sstream << std::hex << value;
    result = sstream.str();
    result = result + address ;
    switch(result.size()){
            case 4 : result="00"+result;
                    break;
            case 5 : result = "0"+result;
                    break;
        }
    return result ;

}
