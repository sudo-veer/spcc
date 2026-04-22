#include <iostream>
#include <vector>
using namespace std;

int main() {
    vector<string> w;
    string s;
    cout << "Enter source code (Type END to finish):\n";
    while(cin >> s && s != "END") w.push_back(s);

    cout << "\nTOKEN TABLE\n-------------------------------------------\nLEXEME\t\tTOKEN TYPE\n-------------------------------------------\n";
    for(int i=0; i<w.size(); i++) {
        s = w[i];
        cout << s << "\t\t";
        if(s == "int" || s == "float") cout << "Keyword\n";
        else if(s == "=" || s == "+" || s == "-" || s == "*") cout << "Operator\n";
        else if(s == ";" || s == ",") cout << "Symbol\n";
        else if(s[0] >= '0' && s[0] <= '9') {
            int dot = 0;
            for(int j=0; j<s.length(); j++) if(s[j] == '.') dot = 1;
            if(dot == 1) cout << "Float Constant\n";
            else cout << "Integer Constant\n";
        }
        else cout << "Identifier\n";
    }

    cout << "\n\nSYMBOL TABLE\n-------------------------------------------\nNAME\t\tCATEGORY\tDATATYPE\n-------------------------------------------\n";
    vector<string> done;
    for(int i=0; i<w.size(); i++) {
        s = w[i];
        bool seen = false;
        for(int k=0; k<done.size(); k++) if(done[k] == s) seen = true;
        if(seen || s == "int" || s == "float" || s == "=" || s == "+" || s == "-" || s == "*" || s == ";" || s == ",") continue;
        
        done.push_back(s);
        cout << s << "\t\t";
        if(s[0] >= '0' && s[0] <= '9') {
            cout << "Constant\t";
            int dot = 0;
            for(int j=0; j<s.length(); j++) if(s[j] == '.') dot = 1;
            if(dot == 1) cout << "float\n"; else cout << "int\n";
        } else {
            cout << "Variable\t";
            if(i > 0) cout << w[i-1] << "\n"; else cout << "unknown\n";
        }
    }
    return 0;
}





