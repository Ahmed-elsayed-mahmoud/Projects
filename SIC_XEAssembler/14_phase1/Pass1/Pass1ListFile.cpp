#include "Pass1ListFile.h"
#include <fstream>
#include "Pass1.h"
#include"Instruction.h"
#include<bits/stdc++.h>
using namespace std ;
std::ofstream outfile ("List File.txt");

Pass1ListFile::Pass1ListFile( vector < Instruction >  x ,map <string,string> y)
{
createListFile(x);
createSymbolTable(y);
    //ctor
}

Pass1ListFile::~Pass1ListFile()
{
    //dtor
}


void Pass1ListFile::createListFile(vector<Instruction> instructionList){



  //  Pass1 pass1;
   // vector<Instruction> instructionList=pass1.getInstructionList();
    for(int i = 0 ; i < instructionList.size() ; i++ ) {
        Instruction inst = instructionList[i];
        if(inst.isError()){
            outfile<<inst.getCommand()<<endl;
            if(inst.getError()!="")
                outfile<<inst.getError()<<endl;

        }else{
            outfile<<inst.getAddress()<<" "<<inst.getCommand()<<endl;
        }
    }


}
void Pass1ListFile::createSymbolTable(std::map<string,string>y){
    outfile<<"-----------------------------------------------------------"<<endl;
    outfile<<"SYMBOL TABLE "<<endl;

    for(auto &i:y){
        outfile<<i.first<<"             "<<i.second<<endl;
     //   cout<<i.first<<"             "<<i.second<<endl;
    }



}
