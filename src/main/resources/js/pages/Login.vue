<template>

 <v-container
 fluid
 fill-height >

        <v-layout
          align-center
          justify-center >

          <v-flex xs12 sm8 md4>
            <v-card elevation="0">
              <v-toolbar light flat >
             <img height=50 alt=""
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMQAAABtCAYAAAD+tc5NAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6Qzg2NDIzRDY3QkM4MTFFNUExRTZFNEYxQUY2N0E1MzgiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6Qzg2NDIzRDc3QkM4MTFFNUExRTZFNEYxQUY2N0E1MzgiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpDODY0MjNENDdCQzgxMUU1QTFFNkU0RjFBRjY3QTUzOCIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpDODY0MjNENTdCQzgxMUU1QTFFNkU0RjFBRjY3QTUzOCIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PtneXHMAAB9kSURBVHja7F0HnFXF1T+7LCwsS++9V2kqKgJPiiVEg72gEIUoiSaxJTbeh4pRXyyYqBH1Q0QNFuIXFA2WoALyUEBAaSq997q7LLALLHznPzN337y79953X93CPb/f3b3v3pm5U86ZU+bMGaKKAP7gI3z5yAMP4oS0CkAMnfjvcr7W89WLAr5j3rB6ECukV4A2TOCrCl9d+LrPG1IPTl8O4Q8O5b8fa0+O8NWZucRWb2g9OL04hD+YwX/Hm55m8fVXb1g9OB1Fptv56qjul/Jl6A7DmVjO9obWg9OHIPzBbP47Tnsymq/ntd9PekPrwenEIf7IVz11P4N1hsVKVMpVz37BRNPHG14PKj5B+IPVKNyaJLlBwJdj4hLjvOH14HTgELdo3GE2E8IC7d3zJi5xpjfEHlRcgvAHUd97tScvhr2XXGKi9uQub4g9qMgc4mIKWZa2Cf2hJIBIjqv7G5mIGnjD7EFFJYjfavcTmSOcKJEi4AOhfKB+ZSoRywMPKhhB+IMN+e9Q9QuE8JpD6le1+xHeMHtQETnENXxVVvefMifYZZsy4JvDf9eoXz2ZmM7whtqDikYQ12r3b7tIP0W7v9Ebag8qDkFIxXig+pVH4Q59eA/lubEp1zva/aXeUHtQkTjEhVpdP2aRqFAjBizUwcnvEZPYtJGkjxOgl2dt8qAiEcQg7f4D0ztYkZrydRsjfRPTu4/Uf7i5X+wNtwcViUMAwBk+17gDEN1w44DCfY8p3+fa/QBvuD0o/wThDzbiv+3UrwUsCh3V3g7W3gFGcfoq2m84/R1W9+d4w+1BReAQvbX7r03vfmf6DT3hSk2PwHrFPPWrh9I3PPCgXBOEvtnnO41zNAxDfnsiMfJUIqxJeOBBOScI3WN1iXZ/A4UW6ihMjPIH22q/F2n33b0h96C8E0Qn9X+HaXX6eoc8+rvl2r23Yu1BOSYIfxBiTnv160ftOcys/RxyXqfdIwLHMY8gPKgIHKKFJhat1p7Dr8kphM5ZTDTtlGJ9kv+uU887eEPuQXkmCH2hbY12f7WLvLrCbRBEM8V1PPCgXBJEM+1+vRKXYDo930XeSy0IArGcmnrD7kF5JYhG2v1e9R+6Q6aLvP2YeKqq+93a88besHtQXgki24IgLnKZF0RjhKLZrz2v4w27B+WVIKpr93vU/wujyG+k3ecRhAduIKPUa+APwopUn2RoGczqhyngW6Xe1ixOF/AdUWmjWW3uZUEQ1RzqAv2iShTlb1FWLOStb+JodrAjYsh+GbcW4qLVwuMBzp/nkLc2/60dw0icUPvRjXIwWbZ0mXePGB/3Y97ANNkZcJTL2e2Qr2oEkbfIMdC17NfmZYcg5Cae/nydR/AtkotuLUyc6i2+RtqU0M0GSezAWJk+7DL9p1ESHLhNjrpH+JurXHKtWTb905n/vqDEwnSHftxJMgbVs4wAp0xv4fH7aAyjs5mv1tpvTEYboxhbGD0e5/q8ZfMegaif4etmvmo4lFPAf9/j634ua7/pLUTg2Y5Eje8EfMdt3p/F18LSJQh/EEiJleOhFL8vUdco07dScWBTAW6tV41t+gm+WXBebOiiDJijn1Yc9fEyIm1g3edNbgdmaastvu/ydYWLcsAFRhGONcCpUAFfUZQSD3Bkmc37HqWjQ/iD1fm6gy/sWIPrxNgYicFM6e1jKKNziVmkdAnCjmXf7ZIYdHiI+7hmGRPDn1T7VHR8ONclMegA0/qvYvh+9xjfJYFDSPkVLPtOvuomoESzqNMyhjJaKHHAgPwYygAbL7R4firCzH/Coo/tZOBLHL6P71itzEMM6UvhG6Hs5WvZ9iqOepT7calu866lEoVXac9i3a0I0fGjBBJEj9QQhFRWfk9yX3O9BJJYjvaNahS+cu0WMCMf1H7nxVDGGGbdzzu0v6GNbjOVSsaFsuMkzWyeQx94maTrSt0o8plhHrdhINcV+tkbcY5Lf2UI+cKBW65yUcf5JDd5vU+hmFvmyYxSSRDpCSAGsMMflDKYSGIAHNLuwX0axEgQ+my2Owligt2svzEK0SrL5vlyRmRYyeysJzVKRTAK+L4UViFrqOmybWu4HHDf723e14qhZj1s8LSpG6klPQ5CyODrCUXl3ZLU7TtNnROL20UjCl972JMi/QGm1R1REE95hGSf+BrLGYjwV6sTi/4QO0FI8ynMX/9DyV3c22viEPVt0uU6lFFbE7Vg596bIoI4aBLVolW+PYgdeqSOIOSZC4uVHJls2KTdNyH7RbOnHJSvmpr1ZlOS6tk4CoKonkJT8OkK3WPRH6InCH8QCtDcKBS5eOXUvZoS3NVBz3iJr384yKFt1P26JNW0eRQEQSnrP49DRE0QGVEQwxCSQcJSHbliLclAAx1t3r/HhJPP9YMIt90C2bD/wdhjvboMcAiD25nrMpysLVULPfyOk0NIK2iXxBGEPziwlIgBsEwRRGeb9/9S3OQk1xNL/vdZEISxU25FkupopRfkkG42LkkQZm74SYRv2LmsHPXwvwR0E4uDIbeWTuTSRy3dBTGAsqaXEjEAFmmNslKm52q/P7NIg9Vtw+y3JIUEkTiRSe7ys8uzwsN/AYu1e+horR10im9j4xDSfPUJxWYPTjRBWNVhtukUoW9Iunvos6mxSQirq6tSKjLBD8cfhI5Tw0V6vd97U8hzFvUfZtN+2O8XeLQg4GcKD2oHV6GNNvoDxNW+0XEI6Y8yRVNISwuWkv3q8kKT2FFIoYjfZghG6Sjmdva2W6U+aPofDYeYRNKsPVtxPatjwUD8VxS7n3uwykGPMHOIn2IRmXA4+mWl3kyJxEGbt1YrnCtt0n6VQu4QiSCaxPE9EMLDBFcHff+CBz9HQRBroiMIGcLl6TLUWDtkXm/xbE2KCaJpigkCe8rh8r2Vx2loqY2IDCptt56SXwo1suYQ0hu4lfZ8u4PEYcshXilFJdoK7CwwWyyeWfn8YKPJsjJEEJFEJjhJYk/AbSS9AawUZ/hnvVNK7t+XK7HOLqTPnlKo004K91joqHbYWekP5F6p9gfR2LJ1uEjAt4brhUU1fT9Egc3OqP0Wzz5Koqwdi8gkV6uxfmLdXvORYTh7e4eFcl5DjdW0FI/IYw7v9jvJ6EkGIPu52mTfNRr9oSSHkPtonyqjMqJ50Att0hVYPPu/JNareQSCsFuLaBbFhADC2RR3OamBZyzPDy8dPaKHBUFExSEQUbtLGSUILLo9qP12K9IdTKL+4MQhVvIEg4Whqjbvw1er/cGWNiKssYH/ZAyGkVQCFgifU1dpwSoLgugRDYcwE8RDZdaGEPAtY6QBAhkLdFWEjCj96cmBUD5w2HSeTB0ikmxvVqyx1dZqrQGBC6aXsdEYTeF+Ybkk9zYcLuV6WSnWVhyiQ2SCkO4ZPahsA7jEOBMyboggQryW5DrF6s5dnh38FjPyLy2D9TKLTD4Kj/J4iOu9nXG9gxsdYnQ5GIg3TaJD6wgy/VLugGQ7x1mJTIdJuqPj+iZKUcuD2AFmeF1/yYzAQWwIQvrnX13mmxvwbabwY3mtdurpLPKVpNbHfpV6Fdf1SnERPVABOURZxY8T5Ozi75Ig5Ip01XLS7Ge1+zMt3htn0sH8904pcAdDkbe6d9IhPEiOHhEzQZSXWQCHKBoergNNMzZWJI1VyRdToOQ19QiizOsRbt+FEURpLsTBQgF3XDgSvkDu4vA8WqxDyPCPBvxS/c8XBJF8iIcgzCJTgU26Hko0s4tJdbgcIKld2zqp8EJnOeBGSjlEhvJbSrWCBwsFLEYzSYZZiW4VOeCbw/X+L9/9gq8bNQIx9KAXOE1OCtoRWWSCB64/CBu92RxsXq2Gu0Mji7IeI+eV4b3lgCDsXDkQq9UpSPKuBHKISPqFIojQUncqAArx00rsiVulJRntThKEP4igVhcpBEmVY6LdKvUBCwKpZsMljMW52RT9scEY5G/KAUEgZu3DMeSbG0Meu5Xo9W7Wo9IpNWc3w027L1fomgQRA2ZelDmZsMgi93sjNmqamE0DvkMpGmg3SrVbPeLFGMSf15IUVifRAGKPdiPTGgq3KLrFC3iy7ohFXDIIonMSOwKuC/CNOo8rOj8J5YNLQDQax9ftJCMI/m8KB7ppwggi4IMN/XJyFxkEnAHh9/9UTgwhEIlxcuznLnNAp7yU88W6X3x1LAq1ITK1TlI3oDE3caOmJ7Gj9zB3eEAhx0lBFPE5lsHd2srH3w5J7yLrMI1md+0/kHUYxbWm9szi9iC6SHvFfazcq/NFvoDPTuF8k685Fs8NnQpIOciF4ptvk44olnA+Ad8OYfTwB6EnIQqK1TmBiAS4xWHj01KHOumew7+34N7r3JSTxhXcTomPJgdFaQg3LJj02UdudZ1B2O8Q8PnJAw/iAIhMiQ5QfFxYe1JBDHLmgVh2C8V2ao4HHpTgEKcSXOZdjKT/8LrWg/IIiT5S6z8kw0rGIwLBHWNAito/k4n3J+3biJ7XwEMLjyASAflCeSx5CGC0AGL4e4raj33L+oaR+yn+c/A8KOc6RKJiFT3neCSqBx6UE4LYn4BysBjyvNedHlQEgkjESuc/U+Q75IEHSdchNvF1RpzlvFHROubabvXpj31KemgP+9dqykhPo7evk9H58wqL6PIpITXkona16Zkhralt3aq04UABPfrVFvrPKuna1LRmFfrbpW1pUNtaVJnLWL7rMPlnbqZvt+SV+F5uQZF4/vdvttOxolPEyem/o7qJfICr3vmZDh6Va5Cd6lfjb7ahfq3kNu7vth2iBz7fSCt3H6GRZzXiq+Rpv7vyj9Owqato0lUdqH09uRXmJGt/2/MK6Y0le2jWhhzxHO/N8NTcbfT5moNheXX498p99Ob3e2jGzV0t3720YCc9NKA5DelQ8uSreZvzaOwXm2nObd0t86LeRj8NnLSiuH35x4roV//8iZ6/rC31alJdpJ2wcCdNH96ValWtJOozb3OuZXtQH6Q3CCLeQ0SwsPdDRSOI5oy8A9qU3PNfNSOdzm2eHfauRa1M2ppbSO2YCIAEmZzm6PGTdGbTbPpgeBfq/uL3tGrvUfpoRFfq3UwuhJ9g7EMZ/x11BrUbv9jye5d3qUvN+PldMzZQlwZZgtgMOK9FDYGUIM7PRp5BbepUFQgN+GXHOnQWf7vt+EXUuk6mZTs258goPqhPzybhJ+ze1LMh9X55KZ06dcoyL5DLLi9g6c7Dol5WefEO0Ll+luX7nAJJ5HZ5zeUa7ctV+UAM+I20Z3MfoA8Bczbm8rNKluVO/2l/mMgUbzj1WQmwLJVpOPOlH4qvHTyDnttcxgvbf0QOwvkt5e+rzqgniOHHPUeo5l/m06aDBWIAgcgNqlcuJgYg+NkT5B797CqV6ALTIHX82xIxQwOu6yaP1eujvnFAcYU+LeTvzg2qCWIQad/7mS6eLEPbNsquLIjGAMygqP+7y6wl5Gk/7qMBr62g44obXXNG+Hrt6A/XFffBxz+Hq50oE893HrI+g3F8cHtx3meD4V4ZS7bni+f4H21eN1zeDv706cbict/R+gQcYnGc+LKoosuVxqxmgEEAX67PoRu61xfI+f6KffQ1z0Kjpq0V3AIzRJGaJvYx4VSvEnJLwvstOaE4a9lVwl2W1u4/St9uPkSD29amOtWkZfw8RYQz1x6kYT0a0PktpHiUkR46qPMAfwdi0ltqBofYZUARsw+0Y+9haw/o/MKTNHdTLhP5cWpco0qJOq3jOpn7wQCUiXfHiqznRYhhdnlBqHiH/9HmdYKCEyfpyq72ThgbebKyKhe9vVJZiWKNEbomibiIXoKfEhb84O69U3E17EPAPg5s4r8wpUoXIyDEEcCnqw8Igji/pey6RdvyxVUzsxJtf/BcMUuDVYMlA8ncwthBLei350rftHUHCopFJMBnayRBnNcim9ItDq3dxwg9ctqaMLHLDXRtmEWTr+5QXE9wNx2mDusskAzQg0VA6E5u4ZHBLemefnKDIPSWBVsPxZxXh033n0O1q1qHl0XfQ7eCaJph0VETr2wv9A1A/4nLaVtuoSIIeajHHJKux7HA5iThHsJPPsD122TxDp6T2FfxEtcdsaRg8h2UCoKAjAo9Ah24Yrfc7AUCqVIprXiGTE9LEwMCqJeVQc1rVaETUewJfPyiVsV6xiNfbhazdbdGUlaH3sCiPSuKGTzgWQlr1zmsF+ESM9y+o0JPgHyuI1ixnJ0mEQxiIOBYkXPjwOUMToe+0+FkBGHbKW+r2pm2+QyxFoYLYwLTwai7mcsaK9WfxkEQBQnGOfD00UwIk1ylDviWM1GASzxKKXDwMzq6cqV0ukPN4iAGKNAL1cwHxTD7sfm04Pae1J0RedyFrYTlxC1A7MJsPH9LnlB+B7KOgTEDgfgHtqBCRkAgB0S373eEy96Y4afeIIMbRvPNbzbn0aTFu2l3/jGavSFXcYMQwg2d8hPN2ySjyOcWnqCsyunFRLI11/n8dv/MTfTKwl3FIhJA0VSxMuw2by9Nia/z+AJhrXrwguaWHA86DfrPiiBGvL+aPll9UFkKT5QgiA/5epliixOa6DCRE6MOPSmV+nFMGJiyk7p91FBmgQyjzwm53J/Pz4H8I3o1oCWMpH9mpQ1m126NsljpzWRlNTSLVmJsqKT1tJxhK2lWnN1h3zTEJcxkd/dtGlYXM0HUygxZUrKquB/OdfsLSnw3XMcoKrYASUtWXW6DxGroHk4Ai1uOCfGNGVrXc9zm1S1SBQ6sd9qP+4WlyQoO25SboRAKG20QEDiW6BtZCcW4eOKwBnzPcDugW1yTLIIwkHPiol303rK9PPu3FAjYh/WIz9YcEPdnN8sWxGCsC+zIO8Yz73E6xEhVgxH2930a08Xta4chY8PqlSN+c+baHPrr11uF7f2WsxpSXy5/DM+gEDuAm3ee35SOHA8h2Hout2/LxBwf8cTFrYR+Itr+3W6Wv+VJa7BKPXahDAhitGFo57r0jNhmI+HW3o1pYFtJpFiT6dUkZLZed8B5U5w5bzQwdfle8rWybv8Y5izG+szrzBmNsnXnvkkxEgSmybVlyCh0J8lwNFmJLhiybMf6MlbAF+tyhMIMy5IgCEbaUazM3tu3mbDNvzS0nZyJmM0/PXebEHfGfbWFnru0jbAeDVYnZ8/ggcBCWt+WNSJypflb88Q3wZ1AEFibgN7y8sKdYrHqas1U+h4jw/oDiZNm+2mIBcJsXitTiY5pdEWXcGsOFiUra3I5uCQuqawX0q+Zi0LkwuxvZwa2y2tW9u0AljwsbEYSfQFzNoQ4XKjW8ogknNoY7e65kTwzv1Wm7KT+IMS/O9yI61z3N7V8WBwQ3q4Y8PZ1pX0fSGiYR421BJjswHKbsMzeSRHJ1yw6ZLIsdEWXutS+XjWxkjz95/2CQxiA/IOYIKB3LGOFD5YqzPBW35MKLNEFreUMuWb/UVEWFOozlSy9eHs+s/8iQWRYMExjcWzpznyhfKPc1nWqUmtWPkGQWAXGynLzmplC1IC1B/VBu3ax7oDFQx309oZZNDjt6ntkgMSHWek3dIv+rWsWGwQaPLmwGJF12JZXSPWyKlMH7h8g7AZFtNANanO7wIVgOh5osYCGvAUnToX1k7l9RjkwBWM9yGjfJtYl9vEzq/bAkmdYmdJMiPRADDL4Pxip7ipjBIFz2ObFQxAe2AMQ7uDDfcQ9XEiMlV7Y/T8c3qVY4c0pOFHu2mbeD/EyyeC80Wwr7VcG27WISp5X7UGCABwJhABYqK0p4N54fvh4UblsW5rF7HoPRb9Bp7mIu1+2uAT0mvYeh/AgHg4BmEAyxlGnKMoZRqV7lJLlRBZDHkx3uR5anL6QZjO7YhvnnCjKga9AlySe9BkLh8CqVMuoOIQHpz1Yr9wEfF8rfcItYHPA9WWIGLLJPu6qBx5ESRAS7qPozhvGSnFmGWnXYCo7p3N6UCEIQsbVvJbCQwQ6AXSOB8tIu245rUfVH6zBV1vtdw8P1ePnECAK2NBuIvszks0wlju/TykjA6xEV53m44qVwk+4Ly7j67ckA057EDdBSKLAXoQ/uiwPdv/3eRCalhIxYLV9sq2xoCQcq5CjCt80IpjPbyAZ3f13Hqq7g7QokA2Huv/VZWpsSx3AA3MwhcQAd9F3FBK4Aey3uFHsBwmV8QRhw1TAN1V7dh7J8JzD1W+s0Sw0pXmVpE8XZuO6akaewGm+0NKgbq+LCN8h4kXo/tX87Cn+PYZgKg74XtTy4JRV1Gm4OC9PptnD969raQL8t6vgzgHfSv59K9+34fuxWhoQBPy7Xiz+fnjfWZUrvy1PUjWeDRLfkUS2S7Vxsvb+7+LbEiBVYFPXC8XndfiDdyn9TodZos1y/P6kxN3aKu/D/G6ZygtPvIlaPiyD44iFVzjNEa0OF5E8nAVrUFgbgyfFFFN70adWJ9jemx7FrINBvt9laoRMCPKHm6eIGCAi/DsKYsBxXiPCiEFCfyp5XgZCPOiHUg4KSyNDb0JEu4LgUBjwYVsXNjBN4Xd6WEyUoZuB/0JyD8q76jcIz3zWWn1VbmUtTXft2+DEt6s09bW+76+lySB5fsYAsjdDh5cb/m2dQHCMGfzdcGzAx4QDW/zBEaa+gaPRHJIHlMCf7FPtPdrXVL2fo6UjhcRjSR6l/Dsl9s1WhEAknTVRn+UkT03CBmtM0p+pCPDGWOB7a1Ud4cH9Fj+/3mKc62h1WK7KrhVdKMuAbzwXvlvMdJHdIhDaZgGnH8b55iWRGFrz36lqUN3A34QFLXGBEW5Vs9kQfaZR9ZkUhlShOg8gacUbyvXYEse3EYpzruU3QvArNVPWj7OdmL0Xc31vU7+BiPUVEr+tpVvAaZ5X7Zyr0rXlZxvU+5+K34f6I1P1GbjcBPUMnGydIo7HtdSTi3dR+oP/FhMvDqbEcQjS7ehbUx0xKfmFKB8OP2j1bK0IMgbTpGQ/YHu7XaRuJigQLErO4okkBEQuH0UyBI4bYoA7I3bi/TlhxCDbNELMQuF9hG9dx9cFnGa0KQ/cOP/J1zOc7rO42i+JcYoLgk2ENzJmf/PhN9NIniRqd7SY4dtdKULZcJ2Ff/kHWh/CyjmDnLcGr1T/je8PsKgjCKEn17FOYpRqa6LAjA/29KWL1OiM/xGs0R8cqWTneBHxYpLHLk1W8mYkwEzT1/W2VPdwrdKXVln0EWbE3wiO5A/q0bGw4LmJYjuEUIfBSk6f5dBPzVS6KQloK8RfMzfbpE185m9Dl/qD6pv1LibOUxb+cFssyw6BoQfsUBMExFvzvtnNWv11sNxYEXv074BvJ1fiEtVoKNvZEXJAfn1DyM7+4FtCAZbytlsiwCxwvUIytw54J4VSBZapK17OgBNNzXuz7fybbjMpemSRL1uJFJWUjjNEsPKS+gsJhdIfdLuGcpsSyZy43W/ErIswo/6I59fczWnutun7LIUrR2z6pYZN/2GjxKUuXHpqqrRmgD9arRJt8gcPKl3kZiUyrtTSmcfKKFcvp1biCUISBQYDkS8+EiKAdPKLBC2U3DlW+Rt9o2bZDSTPMz6iFKiaymIBBdZH0YfbnK+sQ9HGnYLl51WTqPCoBZJ0VLIrxIaOFu/Tlb7yqrICgTAuUoj8iphMAr6ZplwwcT+i/e4tFNeSZdcT+oeUu+0mkHSlY4x02W4o98/afPuEjURRxaH/shRBfij6yjn2b5EDLpq3FF+ikHmP6t8JAg/9QYPozLE1M01tAKD/DiSeIEKEsVWYMP1BKCk4ZPwXLnO2UlciYYWqwwcx6gq7ON9Sk9JuN0O/LTiP9ew7Upn+LlHmbRD/08K0Ka0hz/H/XiZOccD0bTtx8NckD3vZ5ZBmsEImt0eb7bX9dsB3jH9jzcYct9KQCnIs+88f/E5x9WsVN7ODPJIH2aebuAkmRXMQp2GWoYlwHK8cB7OuWi2MU0iRvbEm7iVAh7AnDNjnhyjz2hRKfIgaOzilTIJDhTgV8E1LcnjNyopdv24zOwNRYOseLxbJAr7dqi82qhRPKO53axyWrUj60GiSEUwS1Q8wc7Y1PTN+b7fBhxNKhGkUoWwjfxvT83a2ZduX086iDP0bZ6rx+y75BBHqCJi0blZKzh1K8UvGfsKlSmFvKwgx4JuRojizPpLHxy61ef+A4r7jbfpnl2L3jwu/o+igl5o5nc58zhaye2KUaQO+EpYzuYBmwHDBke0Oj/cHuypZf6WLcYRecJOJQ/1SfdctzBIcKbyOKHOxdowxrIJL1BgkSWSyJ4wcJU++ypWsqdi4T5lJe5iUsUgAlv2jaIxUpGY7nGecbEComzttkADWDKwxPMT1c3KMBLHgPOUxJO3kbqE/yRVkJ0W1p5DdpQtHogALs9+TXHD9Qul0WJC8vISo6A8OVPpFT2WJ/CQCnhRyHuhOL/D/Nmo2v1Yp1a9EUccn1cyPBb3ZavLAxHCZGpuHFHfdr6JVkknvmJSRMhSCjCdtxNM15MHs0VpxknoUHjrmGMnTjXYr+XtrnIeyu4GXlGhg1knGaL+heGKxZ6qJVd+rFLVOKv2rpnLGhLFpKfNepynkEL8KTXnWqXKPamnmmL59VKUxjjX4UMnHZoX9L3Zigio3z+bbRn03cH2xXoCV8e5Kqe3HzxdY9I2hKD8qZvgQ8b7rgB8wzmxUhABDyvtKYc4t1q9CfWxXxmouo7dWx+2qjka7Ue4i1UdmgAJyXxp54MHpAv7gm8IAEPDdY/EO3OQHbxONBx5o8P8CDACK4x6VPRtuMwAAAABJRU5ErkJggg==">
              </v-toolbar>

              <v-card-text>
                <v-form
                 id="app" action="/login"  method="post">
                <v-container v-if="error">
                       <v-alert dense type="error">
                         Неверно введён логин/пароль!
                       </v-alert>
                </v-container>
                  <v-text-field
                    id="username"
                    name="username"
                    :prepend-icon="account"
                    placeholder="Имя пользователя"
                    type="text">
                  </v-text-field>

                  <v-text-field
                    id="password"
                    placeholder="Пароль"
                    name="password"
                    autocomplete="on"
                    :prepend-icon= "lock"
                    type="password" >
                    <v-text-field
                    type="hidden"
                    name="csrf [${csrf.token}]">
                    </v-text-field>
                    </v-text-field>

                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" type="submit" > Войти </v-btn>
              </v-card-actions>
                </v-form>
              </v-card-text>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>

</template>

<script>
import {mdiLock, mdiAccount} from '@mdi/js'

export default {
        data () {
             return {
            error: frontendData.error,
           lock: mdiLock ,
           account: mdiAccount
               }
               }
               }
</script>