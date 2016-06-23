#ifndef PASS2_H
#define PASS2_H
#include"Instruction.h"
#include"bits/stdc++.h"
#include "Check.h"
using namespace std;


class Pass2
{
    public:

        map<string,string> opTableFormat2;
        map<string,string> opTableFormat3;
        ReadOpCode readOpTable;
        string baseRelative ;
        bool base = false;
        Instruction newInstruction;
        Pass2(vector<Instruction> instructionList,map<string,string>x);
        virtual ~Pass2();
        void validateInstruction(vector<Instruction> instructionList);
        void checkFormat2();
        void checkFormat3();
        void checkFormat4();
        string dec_to_hex(string str);
        string create_opject_code(string nixbpe, string address);
        string getInstructionAddress( string pc , string add , int flag, int format,bool isHex);
        string concatinateInstruction(string opCode , string nixbpe , string address);
        Check check;
        map<string,string> registers;
        map<string,string>symbolTable;

    protected:
    private:
};

#endif // PASS2_H
