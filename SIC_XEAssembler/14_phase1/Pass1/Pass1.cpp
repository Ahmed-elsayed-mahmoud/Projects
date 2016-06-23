#include "Pass1.h"
#include <iostream>
#include "Parser.h"
#include<string.h>
#include"Pass1ListFile.h"
using namespace std;
Pass1::Pass1(string fileName,int type)
{
//     ctor
    Parser parser;
    string line = "";
    int size = 0 ;
    string  hLocationCounter = "0";
    if(readFile(fileName)){
        while(size < inputFile.size()){
            line = "";
             line = inputFile[size];
                parser.setSymbolTable(symbolTable);
                parser.parse(line, type);       //0 ---> fixed   1-->free
                Instruction inst = parser.getInstruction();
                symbolTable = parser.getSymbolTable();

                if(inst.getOperator() == "END"){
                    hasEnd = true ;
                }


               if(!inst.isError()){
                    inst.setAddress(hLocationCounter);

                   if( inst.getOperator() == "START"){
                       hLocationCounter = inst.getOperand();
                       inst.setAddress(hLocationCounter);
                       if( inst.hasLabel()  ){
                       if(inst.getLabel().size()!=0){
                            symbolTable[inst.getLabel()] = hLocationCounter;
                            }
                   }
                   }else if(inst.getOperator() == "END"){
                        inst.setAddress(hLocationCounter);
                   }else {
                       if( inst.hasLabel()  ){
                            if(inst.getLabel().size()!=0){
                                symbolTable[inst.getLabel()] = hLocationCounter;
                            }
                        }
                       hLocationCounter = incrementLC( hLocationCounter , inst.getFormat() );
                   }




               }
             instructionList.push_back(inst);

             size++;
         }
         Instruction inst;
        if(!hasEnd){

            inst.setError("hasNoEnd");
            instructionList.push_back(inst);
        }
         Pass1ListFile pass1ListFile(instructionList,symbolTable);


    }


}


bool Pass1:: readFile(string fileName){
    ifstream file(fileName,ios::in);
    std::string read;
    if (!file.is_open()){
        printf("Invalid file\n");
        return false ;
    }else{
        while (getline(file, read)){

            inputFile.push_back(read);

        }
    }
    return true;
}
string Pass1::incrementLC( string hLocationCounter , int add ){
    int n;
    std::istringstream(hLocationCounter) >> std::hex >> n;
    std::stringstream sstream;
    sstream << std::hex << n + add;
    string result = sstream.str();
    switch (result.size()){
         case 1 :
                result = "000"+result;
                break;
         case 2 :
                result = "00"+result;
                break;
         case 3 :
                result ="0"+result;
                break;
    }
    return result;
}
void Pass1::writeListingFile(){
    for(int i = 0 ; i < instructionList.size() ; i++ ) {
        Instruction inst = instructionList[i];
        if(inst.isError()){
         //   cout<<inst.getCommand()<<endl;
            if(inst.getError()!="")cout<<inst.getError()<<endl;

        }else{
            cout<<inst.getAddress()<<" "<<inst.getCommand()<<endl;
        }
    }
}
vector<Instruction> Pass1:: getInstructionList(){
    return instructionList;
}


Pass1::~Pass1()
{
//     dtor
}


