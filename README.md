# Libraries

This builds on [existing research](https://caisgroup.atlassian.net/wiki/spaces/StructuredSolutions/pages/4183490614/PDF+Generation) to establish if we are still using the right PDF generation library for our needs, especially given new requirements to show product holdings which has the potential to produce large tables with complex data.

## HTML to PDF converters

These are Java libraries which are capable of taking HTML as their input and converting it to a PDF document, with varying levels of fidelity, performance and standards support.

### PDFReactor

Our current choice. Good support for modern CSS. Paid-for licence. SVG support.

### Flying Saucer

A popular choice with Java and Spring developers, often combined with Thymeleaf templating to generate initial HTML. Open source. Uses iText 5 or OpenPDF as its backend. Limited support for modern CSS (no CSS custom properties support). No SVG support (can be rendered via Batik to an image).

### openhtmltopdf

Uses apache pdf-box as its backend.

### pdfHtml

A commercial offering from the same team as iText (presumably uses iText as its backend):

`pdfHTML is an iText Core add-on for Java and C# (.NET) that allows you to easily convert HTML and CSS into standards compliant PDFs that are accessible, searchable and usable for indexing.
`

## Low-level PDF libraries

These are Java libraries which tend to operate at a lower level of abstraction; some of them are used as the backend for the converters listed above.

### iText <= 5

Open source.

### iText > 5

After version 5 the author moved iText to a closed source commercial licence.

### OpenPDF

Based on a fork of iText 5.

### Apache pdf-box

Not much documentation.

### Apache FOP

Supports SVG.

## Summary

iText, OpenPDF and pdf-box are all deemed unsuitable, as their input formats are extremely low-level. Apache FOP is the only low-level library deemed suitable as its input format is similar to HTML and it has good SVG support.

Of the remaining libraries, we can sum up their suitability in a table, with the rationale given afterwards.

|             | PDFreactor | Flying Saucer | Apache FOP |
|-------------|------------|---------------|------------|
| Familiarity  | ✅          | -             | -          |
| SVG Support | ✅          | ❌             | ✅          |          
| CSS Support | ✅          | -             | ❌          |
| Speed       | ❌          | ✅             | ?          |

## PDFreactor
- Scores positively on familiarity, since it is already in use
- Scores positively on SVG support, since it is supported natively
- Scores positively on CSS support, since through experience we know that it can render most modern webpages well
- Scores negatively on speed (see benchmarking).
## Flying Saucer
- Scores neutral on familiarity, since we don't currently use it but there are developers in the team who have used it before
- Scores negatively on SVG support, although there are work-arounds
- Scores neutral on CSS support, since it is easy to rewrite our CSS to support the CAIS style
- Scores positively on speed (see benchmarking).

## Apache FOP
- Scores neutral on familiarity, since we don't currently use it but there are developers in the team who have used it before
- Scores positively on SVG support, since it is supported natively
- Scores negatively on CSS support, since the input format is an XML dialect unfamiliar to frontend developers
- Scores unknown on speed, since we have not benchmarked it.

# PDF Sample Code / Benchmarks

Our existing PDFreactor usage within our cloud estate has been observed to be slow and memory intensive. This has given rise to a need to gather benchmark data to ask:

- Is our current library slow?
- Are there ways we could use or configure to be faster?
- Does our cloud environment impact performance negatively?
- Should we use another library?

## About the files

These are the input files:

- [clean-table-cleaned-up-250.html](resources/clean-table-cleaned-up-250.html)
- [clean-table-cleaned-up-1500.html](resources/clean-table-cleaned-up-1500.html)

They are synthetic HTML with 250 and 1500 rows, respectively, and feature sub-rows and some basic styling, as well as a CSS pie chart.

The CSS has been cleaned up (it is not the full CAIS CSS) and simplified (eg, flying saucer doesn't understand CSS custom properties).

These are sample output files, with the 1500 row variant:

- [out-flying-saucer.pdf](test-flying-saucer/out-flying-saucer.pdf)
- [out-pdf-reactor.pdf](test-pdfreactor/out-pdf-reactor.pdf)
- [out-html2pdf-reactor.pdf](test-html2pdf/out-html2pdf.pdf)

## Benchmarks

Times are mean based on 10 executions to allow for JVM/library warm-up (it's not a hugely scientific test, but still a useful yardstick).

### 250 rows

| PDFreactor | Flying Saucer | html2pdf | Speedup (PDFreactor vs Flying Saucer) |
|------------|---------------|----------|---------|
| 3.581s     | 0.426s        | 0.915s   | 8.406   |

### 1500 rows

| PDFreactor | Flying Saucer | html2pdf | Speedup (PDFreactor vs Flying Saucer) |
|------------|---------------|----------|---------|
| 12.51s     | 1.647s        | 4.43s    | 7.596   |

## Commentary

PDFreactor is much slower but we know from other testing that it can handle the full, unmodified CAIS CSS and natively render SVG.

Flying Saucer is much faster but less capable: we would have to render any SVGs as images and simplify the CSS.

html2pdf ignores our CSS and SVG without emitting any errors or warnings.

Our existing PDFreactor usage within our cloud estate has been observed to be slow and memory intensive. Observing PDFreactor logs for a large document, the gap between `Layout phase 1 of 3 reached` and `Layout phase 3 of 3 reached` messages in our cloud environment is approx 1 minute, whereas this process takes approx 5 seconds locally. The message `Could not use font cache on file system: Access Denied: /.PDFreactor` was observed in the cloud environment. [Setting the font cache option](https://github.com/cais-group/structured-products/pull/307/files) has proved effective at lowering this layout time dramatically.

Therefore, we may be able to ameliorate PDFreactor issues with a combination of:
- Setting font cache
- Increasing CPU limits
- Increasing memory limits
- Asynchronous PDF generation (see below)

## Asynchronous PDF Generation

See [here](doc/async-use-cases.md) for a discussion of how to approach long-running PDF report requests.
