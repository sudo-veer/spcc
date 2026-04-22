
#include <iostream>
#include <vector>
#include <string>
using namespace std;

int pr(char c) {
    if(c == '*' || c == '/') return 2;
    if(c == '+' || c == '-') return 1;
    return 0;
}

int main() {
    string in, post = "";
    vector<char> st;
    cout << "Enter INFIX (e.g., a+b*c-d): ";
    cin >> in;

    for(char c : in) {
        if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) post += c;
        else {
            while(st.size() && pr(st.back()) >= pr(c)) {
                post += st.back(); st.pop_back();
            }
            st.push_back(c);
        }
    }
    while(st.size()) { post += st.back(); st.pop_back(); }

    cout << "\n[1] INFIX: " << in << "\n[2] POSTFIX: " << post << "\n\n";
    cout << ">>> THREE-ADDRESS CODE (TAC) <<<\n";

    vector<string> s, op, a1, a2, res;
    int t = 1;

    for(char c : post) {
        if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            s.push_back(string(1, c));
        } else {
            string v2 = s.back(); s.pop_back();
            string v1 = s.back(); s.pop_back();
            string tr = "t" + to_string(t++);

            cout << tr << " = " << v1 << " " << c << " " << v2 << "\n";

            op.push_back(string(1, c));
            a1.push_back(v1);
            a2.push_back(v2);
            res.push_back(tr);
            s.push_back(tr);
        }
    }

    cout << "\n>>> QUADRUPLE <<<\nOP\tARG1\tARG2\tRES\n";
    for(int i = 0; i < op.size(); i++) {
        cout << op[i] << "\t" << a1[i] << "\t" << a2[i] << "\t" << res[i] << "\n";
    }

    cout << "\n>>> TRIPLE <<<\nIDX\tOP\tARG1\tARG2\n";
    for(int i = 0; i < op.size(); i++) {
        string t1 = a1[i], t2 = a2[i];
        if(t1[0] == 't') t1 = "(" + to_string(t1[1] - '1') + ")";
        if(t2[0] == 't') t2 = "(" + to_string(t2[1] - '1') + ")";
        cout << "(" << i << ")\t" << op[i] << "\t" << t1 << "\t" << t2 << "\n";
    }
    return 0;
}


