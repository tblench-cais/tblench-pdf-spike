# PDF Sample Code / Benchmarks

## About the files

These are the input files:

- [clean-table-cleaned-up-250.html](resources/clean-table-cleaned-up-250.html)
- [clean-table-cleaned-up-1500.html](resources/clean-table-cleaned-up-1500.html)

They are synthetic HTML with 250 and 1500 rows, respectively, and feature sub-rows and some basic styling, as well as a CSS pie chart.

The CSS has been cleaned up (it is not the full CAIS CSS) and simplified (eg, flying saucer doesn't understand CSS custom properties).

These are sample output files, with the 1500 row variant:

- [out-flying-saucer.pdf](test-flying-saucer/out-flying-saucer.pdf)
- [out-pdf-reactor.pdf](test-pdfreactor/out-pdf-reactor.pdf)


## Benchmarks

Times are mean based on 10 executions to allow for JVM/library warm-up (it's not a hugely scientific test, but still a useful yardstick).

### 250 rows

| PDFreactor | Flying Saucer  |
|--|--|
| 3.581s | 0.426s |

### 1500 rows

| PDFreactor | Flying Saucer  |
|--|--|
| 12.51s | 1.647s |
