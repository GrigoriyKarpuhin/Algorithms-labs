#include <iostream>

using namespace std;

int main() {
    long long n, x, y, a0, m, z, t, b0;
    cin >> n >> x >> y >> a0 >> m >> z >> t >> b0;
    long long a[n];
    for (int i = 0; i < n; i++) {
        a[i] = a0;
        a0 = (x * a0 + y) % (1 << 16);
    }
    for (int i = 1; i < n; i++) {
        a[i] = a[i] + a[i - 1];
    }
    unsigned long long b[2 * m];
    for (int i = 0; i < 2 * m; i++) {
        b[i] = b0;
        b0 = (z * b0 + t) % (1 << 30);
    }
    long long ans = 0;
    for (int i = 0; i < m; i++) {
        unsigned long long l = min(b[2 * i] % n, b[2 * i + 1] % n);
        unsigned long long r = max(b[2 * i] % n, b[2 * i + 1] % n);
        if (l == 0) {
            ans += a[r];
        } else {
            ans += a[r] - a[l - 1];
        }
    }
    cout << ans << endl;
}