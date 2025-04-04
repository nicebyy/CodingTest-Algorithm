#define _CRT_SECURE_NO_WARNINGS
#pragma warning(disable: 4996)
#include <iostream>
#include <map>
#include <vector>
#include <string>
#include <algorithm>
#define endl '\n'
using namespace std;

map<string, vector<string>> tree;
map<string, int> fd;

void find_files(vector<string>& file, const string& target) {
    for (const auto& child : tree[target]) {
        if (fd[child] == 1)
            find_files(file, child);
        else
            file.push_back(child);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);   cout.tie(NULL);

    int n, m;
    cin >> n >> m;
    cin.ignore();

    fd["main"] = 1;
    for (int i = 0; i < n + m; i++) {
        string p, c;
        int f;
        cin >> p >> c >> f;
        
        tree[p].push_back(c);
        fd[c] = f;
    }

    int count;
    cin >> count;
    cin.ignore();

    for (int i = 0; i < count; i++) {
        string loca;
        cin >> loca;

        int index = loca.size() - 1;
        while (index != 0 && loca[index] != '/')
            index--;
        
        string target;
        if (index == 0)
            target = loca.substr(index);
        else
            target = loca.substr(index + 1);
        
        vector<string> v;
        find_files(v, target);

        int cnt = v.size();
        sort(v.begin(), v.end());
        v.erase(unique(v.begin(), v.end()), v.end());
        int kinds = v.size();

        cout << kinds << " " << cnt << endl;
    }
}
