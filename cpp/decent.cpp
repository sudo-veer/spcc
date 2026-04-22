#include <algorithm>
#include <iomanip>
#include <iostream>
#include <string>
#include <vector>

using namespace std;

string inputExpr;
size_t pos = 0;
vector<string> callStack;

void skipSpaces() {
    while (pos < inputExpr.size() && isspace(static_cast<unsigned char>(inputExpr[pos]))) {
        pos++;
    }
}

bool peek(char c) {
    skipSpaces();
    return pos < inputExpr.size() && inputExpr[pos] == c;
}

bool match(char c) {
    if (peek(c)) {
        pos++;
        return true;
    }
    return false;
}

bool readId() {
    skipSpaces();
    if (pos >= inputExpr.size()) return false;

    char c = inputExpr[pos];
    if (!isalpha(static_cast<unsigned char>(c)) && c != '_') return false;

    pos++;
    while (pos < inputExpr.size()) {
        char ch = inputExpr[pos];
        if (!isalnum(static_cast<unsigned char>(ch)) && ch != '_') break;
        pos++;
    }
    return true;
}

bool readNum() {
    skipSpaces();
    size_t start = pos;
    bool hasDot = false;

    while (pos < inputExpr.size()) {
        char c = inputExpr[pos];
        if (isdigit(static_cast<unsigned char>(c))) {
            pos++;
        } else if (c == '.' && !hasDot) {
            hasDot = true;
            pos++;
        } else {
            break;
        }
    }

    return pos > start;
}

void printRow(const string& action) {
    string stackView = "$";
    for (auto it = callStack.rbegin(); it != callStack.rend(); ++it) {
        stackView += *it;
    }

    string inputView = inputExpr.substr(min(pos, inputExpr.size()));
    inputView.erase(inputView.begin(), find_if(inputView.begin(), inputView.end(), [](unsigned char ch) {
        return !isspace(ch);
    }));
    inputView += "$";

    cout << left << setw(20) << stackView << " "
         << left << setw(20) << inputView << " "
         << action << "\n";
}

bool E();
bool EP();
bool T();
bool TP();
bool F();

bool E() {
    printRow("E -> T E'");
    return T() && EP();
}

bool EP() {
    callStack.push_back("E'");
    if (!peek('+') && !peek('-')) {
        printRow("E' -> e");
        callStack.pop_back();
        return true;
    }

    char op = inputExpr[pos++];
    printRow(string("Match ") + op);
    bool ok = T() && EP();
    callStack.pop_back();
    return ok;
}

bool T() {
    callStack.push_back("T");
    printRow("T -> F T'");
    bool ok = F() && TP();
    callStack.pop_back();
    return ok;
}

bool TP() {
    callStack.push_back("T'");
    if (!peek('*') && !peek('/')) {
        printRow("T' -> e");
        callStack.pop_back();
        return true;
    }

    char op = inputExpr[pos++];
    printRow(string("Match ") + op);
    bool ok = F() && TP();
    callStack.pop_back();
    return ok;
}

bool F() {
    callStack.push_back("F");
    bool ok;

    if (match('(')) {
        printRow("Match (");
        ok = E() && match(')');
        if (ok) printRow("Match )");
    } else if (readId()) {
        printRow("Match id");
        ok = true;
    } else if (readNum()) {
        printRow("Match num");
        ok = true;
    } else {
        printRow("Error in F");
        ok = false;
    }

    callStack.pop_back();
    return ok;
}

bool endInput() {
    skipSpaces();
    return pos == inputExpr.size();
}

int main() {
    cout << "Enter expression (e.g., a+b*(c-2)):\n";
    getline(cin, inputExpr);

    cout << left << setw(20) << "Stack" << " "
         << left << setw(20) << "Input" << " "
         << "Action\n";

    callStack.push_back("E");
    printRow("Start");

    bool accepted = E() && endInput();
    printRow(accepted ? "Accepted" : "Rejected");
    return 0;
}



