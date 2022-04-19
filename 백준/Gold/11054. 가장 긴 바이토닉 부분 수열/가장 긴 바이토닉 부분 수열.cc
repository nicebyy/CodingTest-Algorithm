#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int main()
{
    int n;
    cin >> n;

    vector<int> a(n);
    vector<int> d1(n);
    vector<int> d2(n);

    for (int i = 0; i < n; i++)
        cin >> a[i];


    // 왼->오 순으로 A[i]를 원소로 포함하는 가장 긴 증가하는 부분 수열의 길이 구하기
    for (int i = 0; i < n; i++)
    {
        d1[i] = 1;

        for (int j = 0 ; j < n; j++)
        {
            if (a[j] < a[i] && d1[i] < d1[j]+1)
                d1[i] = d1[j]+1;
        }
    }

    // 오->왼 순으로 A[i]를 원소로 포함하는 가장 긴 증가하는 부분 수열의 길이 구하기
    for (int i = n-1; i >= 0; i--)
    {
        d2[i] = 1;

        for (int j = n-1 ; j > i; j--)
        {
            if (a[j] < a[i] && d2[i] < d2[j]+1)
                d2[i] = d2[j]+1;
        }
    }


    int ans = d1[0] + d2[0] - 1;

    for (int i = 0; i < n; i++)
    {
        if (ans < d1[i] + d2[i] - 1)
            ans = d1[i] + d2[i] - 1;
    }

    cout << ans << "\n";
}