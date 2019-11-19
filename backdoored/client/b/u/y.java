package r.k.b.u;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.BufferUtils;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;

public final class y
{
    private static y uc;
    private final FloatBuffer ua;
    private IntBuffer um;
    private FloatBuffer uh;
    private FloatBuffer zd;
    private y$u zs;
    private y$u[] zl;
    private y$u[] zy;
    private y$u zx;
    private double zk;
    private double zq;
    private double zv;
    private double zj;
    private double ze;
    private double zo;
    private double zt;
    private double zn;
    private y$p zi;
    private y$p zp;
    private y$p zr;
    private y$p zf;
    private float zb;
    private float zw;
    private y$u zg;
    public static final boolean zu;
    public static final int zz;
    public static final boolean zc;
    
    private y() {
        super();
        this.ua = BufferUtils.createFloatBuffer(3);
    }
    
    public static y ls() {
        if (y.uc == null) {
            y.uc = new y();
        }
        return y.uc;
    }
    
    public void d(final IntBuffer um, final FloatBuffer uh, final FloatBuffer zd, final double zv, final double zj) {
        this.um = um;
        this.uh = uh;
        this.zd = zd;
        this.zv = zv;
        this.zj = zj;
        final float zb = (float)Math.toDegrees(Math.atan(1.0 / this.zd.get(5)) * 2.0);
        this.zb = zb;
        this.zk = this.um.get(2);
        this.zq = this.um.get(3);
        this.zw = (float)Math.toDegrees(2.0 * Math.atan(this.zk / this.zq * Math.tan(Math.toRadians(this.zb) / 2.0)));
        final y$u y$u = new y$u(this.uh.get(12), this.uh.get(13), this.uh.get(14));
        final y$u y$u2 = new y$u(this.uh.get(0), this.uh.get(1), this.uh.get(2));
        final y$u y$u3 = new y$u(this.uh.get(4), this.uh.get(5), this.uh.get(6));
        final y$u y$u4 = new y$u(this.uh.get(8), this.uh.get(9), this.uh.get(10));
        final y$u y$u5 = new y$u(0.0, 1.0, 0.0);
        final y$u y$u6 = new y$u(1.0, 0.0, 0.0);
        final y$u y$u7 = new y$u(0.0, 0.0, 1.0);
        double n = Math.toDegrees(Math.atan2(y$u6.y(y$u2).xe(), y$u6.l(y$u2))) + 180.0;
        if (y$u4.swv < 0.0) {
            n = 360.0 - n;
        }
        if ((-y$u4.swj > 0.0 && n >= 90.0 && n < 270.0) || (y$u4.swj > 0.0 && (n < 90.0 || n >= 270.0))) {
            Math.toDegrees(Math.atan2(y$u5.y(y$u3).xe(), y$u5.l(y$u3)));
        }
        final double n2 = -Math.toDegrees(Math.atan2(y$u5.y(y$u3).xe(), y$u5.l(y$u3)));
        this.zg = this.d(n, n2);
        final Matrix4f matrix4f = new Matrix4f();
        matrix4f.load(this.uh.asReadOnlyBuffer());
        matrix4f.invert();
        this.zs = new y$u(matrix4f.m30, matrix4f.m31, matrix4f.m32);
        this.zl = this.d(this.zs.swv, this.zs.swj, this.zs.swe, n, n2, zb, 1.0, this.zk / this.zq);
        this.zy = this.d(this.zs.swv, this.zs.swj, this.zs.swe, n - 180.0, -n2, zb, 1.0, this.zk / this.zq);
        this.zx = this.d(n, n2).xj();
        this.ze = Math.toDegrees(Math.acos(this.zq * zj / Math.sqrt(this.zk * zv * this.zk * zv + this.zq * zj * this.zq * zj)));
        this.zo = 360.0 - this.ze;
        this.zt = this.zo - 180.0;
        this.zn = this.ze + 180.0;
        this.zf = new y$p(this.zk * this.zv, 0.0, 0.0, 0.0, 1.0, 0.0);
        this.zi = new y$p(0.0, 0.0, 0.0, 1.0, 0.0, 0.0);
        this.zr = new y$p(0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
        this.zp = new y$p(0.0, this.zq * this.zj, 0.0, 1.0, 0.0, 0.0);
    }
    
    public y$l d(final double p0, final double p1, final double p2, final y$i p3, final boolean p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: aconst_null    
        //     5: areturn        
        //     6: nop            
        //     7: nop            
        //     8: aload_0        
        //     9: getfield        r/k/b/u/y.um:Ljava/nio/IntBuffer;
        //    12: ifnull          1156
        //    15: aload_0        
        //    16: getfield        r/k/b/u/y.uh:Ljava/nio/FloatBuffer;
        //    19: ifnull          1156
        //    22: aload_0        
        //    23: getfield        r/k/b/u/y.zd:Ljava/nio/FloatBuffer;
        //    26: ifnull          1156
        //    29: new             Lr/k/b/u/y$u;
        //    32: dup            
        //    33: dload_1        
        //    34: dload_3        
        //    35: dload           5
        //    37: invokespecial   r/k/b/u/y$u.<init>:(DDD)V
        //    40: astore          9
        //    42: aload_0        
        //    43: aload_0        
        //    44: getfield        r/k/b/u/y.zl:[Lr/k/b/u/y$u;
        //    47: aload_0        
        //    48: getfield        r/k/b/u/y.zs:Lr/k/b/u/y$u;
        //    51: dload_1        
        //    52: dload_3        
        //    53: dload           5
        //    55: invokevirtual   r/k/b/u/y.d:([Lr/k/b/u/y$u;Lr/k/b/u/y$u;DDD)[Z
        //    58: astore          10
        //    60: aload           10
        //    62: iconst_0       
        //    63: baload         
        //    64: ifne            88
        //    67: aload           10
        //    69: iconst_1       
        //    70: baload         
        //    71: ifne            88
        //    74: aload           10
        //    76: iconst_2       
        //    77: baload         
        //    78: ifne            88
        //    81: aload           10
        //    83: iconst_3       
        //    84: baload         
        //    85: ifeq            90
        //    88: iconst_1       
        //    89: nop            
        //    90: iconst_0       
        //    91: istore          11
        //    93: iload           11
        //    95: ifeq            1062
        //    98: aload           9
        //   100: aload_0        
        //   101: getfield        r/k/b/u/y.zs:Lr/k/b/u/y$u;
        //   104: invokevirtual   r/k/b/u/y$u.s:(Lr/k/b/u/y$u;)Lr/k/b/u/y$u;
        //   107: aload_0        
        //   108: getfield        r/k/b/u/y.zx:Lr/k/b/u/y$u;
        //   111: invokevirtual   r/k/b/u/y$u.l:(Lr/k/b/u/y$u;)D
        //   114: dconst_0       
        //   115: dcmpg          
        //   116: ifgt            121
        //   119: iconst_1       
        //   120: nop            
        //   121: iconst_0       
        //   122: istore          12
        //   124: aload_0        
        //   125: aload_0        
        //   126: getfield        r/k/b/u/y.zy:[Lr/k/b/u/y$u;
        //   129: aload_0        
        //   130: getfield        r/k/b/u/y.zs:Lr/k/b/u/y$u;
        //   133: dload_1        
        //   134: dload_3        
        //   135: dload           5
        //   137: invokevirtual   r/k/b/u/y.d:([Lr/k/b/u/y$u;Lr/k/b/u/y$u;DDD)[Z
        //   140: astore          13
        //   142: aload           13
        //   144: iconst_0       
        //   145: baload         
        //   146: ifne            170
        //   149: aload           13
        //   151: iconst_1       
        //   152: baload         
        //   153: ifne            170
        //   156: aload           13
        //   158: iconst_2       
        //   159: baload         
        //   160: ifne            170
        //   163: aload           13
        //   165: iconst_3       
        //   166: baload         
        //   167: ifeq            172
        //   170: iconst_1       
        //   171: nop            
        //   172: iconst_0       
        //   173: istore          14
        //   175: iload           8
        //   177: ifeq            185
        //   180: iload           14
        //   182: ifeq            198
        //   185: iload           14
        //   187: ifeq            925
        //   190: aload           7
        //   192: getstatic       r/k/b/u/y$i.syk:Lr/k/b/u/y$i;
        //   195: if_acmpeq       925
        //   198: iload           8
        //   200: ifeq            208
        //   203: iload           14
        //   205: ifeq            221
        //   208: aload           7
        //   210: getstatic       r/k/b/u/y$i.syx:Lr/k/b/u/y$i;
        //   213: if_acmpne       702
        //   216: iload           14
        //   218: ifeq            702
        //   221: dconst_0       
        //   222: dstore          15
        //   224: dconst_0       
        //   225: dstore          17
        //   227: dload_1        
        //   228: d2f            
        //   229: dload_3        
        //   230: d2f            
        //   231: dload           5
        //   233: d2f            
        //   234: aload_0        
        //   235: getfield        r/k/b/u/y.uh:Ljava/nio/FloatBuffer;
        //   238: aload_0        
        //   239: getfield        r/k/b/u/y.zd:Ljava/nio/FloatBuffer;
        //   242: aload_0        
        //   243: getfield        r/k/b/u/y.um:Ljava/nio/IntBuffer;
        //   246: aload_0        
        //   247: getfield        r/k/b/u/y.ua:Ljava/nio/FloatBuffer;
        //   250: invokestatic    org/lwjgl/util/glu/GLU.gluProject:(FFFLjava/nio/FloatBuffer;Ljava/nio/FloatBuffer;Ljava/nio/IntBuffer;Ljava/nio/FloatBuffer;)Z
        //   253: ifeq            413
        //   256: iload           12
        //   258: ifeq            347
        //   261: aload_0        
        //   262: getfield        r/k/b/u/y.zk:D
        //   265: aload_0        
        //   266: getfield        r/k/b/u/y.zv:D
        //   269: dmul           
        //   270: aload_0        
        //   271: getfield        r/k/b/u/y.ua:Ljava/nio/FloatBuffer;
        //   274: iconst_0       
        //   275: invokevirtual   java/nio/FloatBuffer.get:(I)F
        //   278: f2d            
        //   279: aload_0        
        //   280: getfield        r/k/b/u/y.zv:D
        //   283: dmul           
        //   284: dsub           
        //   285: aload_0        
        //   286: getfield        r/k/b/u/y.zk:D
        //   289: aload_0        
        //   290: getfield        r/k/b/u/y.zv:D
        //   293: dmul           
        //   294: ldc2_w          2.0
        //   297: ddiv           
        //   298: dsub           
        //   299: dstore          15
        //   301: aload_0        
        //   302: getfield        r/k/b/u/y.zq:D
        //   305: aload_0        
        //   306: getfield        r/k/b/u/y.zj:D
        //   309: dmul           
        //   310: aload_0        
        //   311: getfield        r/k/b/u/y.zq:D
        //   314: aload_0        
        //   315: getfield        r/k/b/u/y.ua:Ljava/nio/FloatBuffer;
        //   318: iconst_1       
        //   319: invokevirtual   java/nio/FloatBuffer.get:(I)F
        //   322: f2d            
        //   323: dsub           
        //   324: aload_0        
        //   325: getfield        r/k/b/u/y.zj:D
        //   328: dmul           
        //   329: dsub           
        //   330: aload_0        
        //   331: getfield        r/k/b/u/y.zq:D
        //   334: aload_0        
        //   335: getfield        r/k/b/u/y.zj:D
        //   338: dmul           
        //   339: ldc2_w          2.0
        //   342: ddiv           
        //   343: dsub           
        //   344: dstore          17
        //   346: nop            
        //   347: aload_0        
        //   348: getfield        r/k/b/u/y.ua:Ljava/nio/FloatBuffer;
        //   351: iconst_0       
        //   352: invokevirtual   java/nio/FloatBuffer.get:(I)F
        //   355: f2d            
        //   356: aload_0        
        //   357: getfield        r/k/b/u/y.zv:D
        //   360: dmul           
        //   361: aload_0        
        //   362: getfield        r/k/b/u/y.zk:D
        //   365: aload_0        
        //   366: getfield        r/k/b/u/y.zv:D
        //   369: dmul           
        //   370: ldc2_w          2.0
        //   373: ddiv           
        //   374: dsub           
        //   375: dstore          15
        //   377: aload_0        
        //   378: getfield        r/k/b/u/y.zq:D
        //   381: aload_0        
        //   382: getfield        r/k/b/u/y.ua:Ljava/nio/FloatBuffer;
        //   385: iconst_1       
        //   386: invokevirtual   java/nio/FloatBuffer.get:(I)F
        //   389: f2d            
        //   390: dsub           
        //   391: aload_0        
        //   392: getfield        r/k/b/u/y.zj:D
        //   395: dmul           
        //   396: aload_0        
        //   397: getfield        r/k/b/u/y.zq:D
        //   400: aload_0        
        //   401: getfield        r/k/b/u/y.zj:D
        //   404: dmul           
        //   405: ldc2_w          2.0
        //   408: ddiv           
        //   409: dsub           
        //   410: dstore          17
        //   412: nop            
        //   413: new             Lr/k/b/u/y$l;
        //   416: dup            
        //   417: dconst_0       
        //   418: dconst_0       
        //   419: getstatic       r/k/b/u/y$l$s.seq:Lr/k/b/u/y$l$s;
        //   422: invokespecial   r/k/b/u/y$l.<init>:(DDLr/k/b/u/y$l$s;)V
        //   425: areturn        
        //   426: new             Lr/k/b/u/y$u;
        //   429: dup            
        //   430: dload           15
        //   432: dload           17
        //   434: dconst_0       
        //   435: invokespecial   r/k/b/u/y$u.<init>:(DDD)V
        //   438: invokevirtual   r/k/b/u/y$u.xo:()Lr/k/b/u/y$u;
        //   441: astore          19
        //   443: aload           19
        //   445: getfield        r/k/b/u/y$u.swv:D
        //   448: dstore          15
        //   450: aload           19
        //   452: getfield        r/k/b/u/y$u.swj:D
        //   455: dstore          17
        //   457: new             Lr/k/b/u/y$p;
        //   460: dup            
        //   461: aload_0        
        //   462: getfield        r/k/b/u/y.zk:D
        //   465: aload_0        
        //   466: getfield        r/k/b/u/y.zv:D
        //   469: dmul           
        //   470: ldc2_w          2.0
        //   473: ddiv           
        //   474: aload_0        
        //   475: getfield        r/k/b/u/y.zq:D
        //   478: aload_0        
        //   479: getfield        r/k/b/u/y.zj:D
        //   482: dmul           
        //   483: ldc2_w          2.0
        //   486: ddiv           
        //   487: dconst_0       
        //   488: dload           15
        //   490: dload           17
        //   492: dconst_0       
        //   493: invokespecial   r/k/b/u/y$p.<init>:(DDDDDD)V
        //   496: astore          20
        //   498: aload           19
        //   500: getfield        r/k/b/u/y$u.swj:D
        //   503: aload           19
        //   505: getfield        r/k/b/u/y$u.swv:D
        //   508: aload           19
        //   510: getfield        r/k/b/u/y$u.swv:D
        //   513: dmul           
        //   514: aload           19
        //   516: getfield        r/k/b/u/y$u.swj:D
        //   519: aload           19
        //   521: getfield        r/k/b/u/y$u.swj:D
        //   524: dmul           
        //   525: dadd           
        //   526: invokestatic    java/lang/Math.sqrt:(D)D
        //   529: ddiv           
        //   530: invokestatic    java/lang/Math.acos:(D)D
        //   533: invokestatic    java/lang/Math.toDegrees:(D)D
        //   536: dstore          21
        //   538: dload           15
        //   540: dconst_0       
        //   541: dcmpg          
        //   542: ifge            553
        //   545: ldc2_w          360.0
        //   548: dload           21
        //   550: dsub           
        //   551: dstore          21
        //   553: new             Lr/k/b/u/y$u;
        //   556: dup            
        //   557: dconst_0       
        //   558: dconst_0       
        //   559: dconst_0       
        //   560: invokespecial   r/k/b/u/y$u.<init>:(DDD)V
        //   563: astore          23
        //   565: dload           21
        //   567: aload_0        
        //   568: getfield        r/k/b/u/y.ze:D
        //   571: dcmpl          
        //   572: iflt            597
        //   575: dload           21
        //   577: aload_0        
        //   578: getfield        r/k/b/u/y.zt:D
        //   581: dcmpg          
        //   582: ifge            597
        //   585: aload_0        
        //   586: getfield        r/k/b/u/y.zf:Lr/k/b/u/y$p;
        //   589: aload           20
        //   591: invokevirtual   r/k/b/u/y$p.d:(Lr/k/b/u/y$p;)Lr/k/b/u/y$u;
        //   594: astore          23
        //   596: nop            
        //   597: dload           21
        //   599: aload_0        
        //   600: getfield        r/k/b/u/y.zt:D
        //   603: dcmpl          
        //   604: iflt            629
        //   607: dload           21
        //   609: aload_0        
        //   610: getfield        r/k/b/u/y.zn:D
        //   613: dcmpg          
        //   614: ifge            629
        //   617: aload_0        
        //   618: getfield        r/k/b/u/y.zi:Lr/k/b/u/y$p;
        //   621: aload           20
        //   623: invokevirtual   r/k/b/u/y$p.d:(Lr/k/b/u/y$p;)Lr/k/b/u/y$u;
        //   626: astore          23
        //   628: nop            
        //   629: dload           21
        //   631: aload_0        
        //   632: getfield        r/k/b/u/y.zn:D
        //   635: dcmpl          
        //   636: iflt            661
        //   639: dload           21
        //   641: aload_0        
        //   642: getfield        r/k/b/u/y.zo:D
        //   645: dcmpg          
        //   646: ifge            661
        //   649: aload_0        
        //   650: getfield        r/k/b/u/y.zr:Lr/k/b/u/y$p;
        //   653: aload           20
        //   655: invokevirtual   r/k/b/u/y$p.d:(Lr/k/b/u/y$p;)Lr/k/b/u/y$u;
        //   658: astore          23
        //   660: nop            
        //   661: aload_0        
        //   662: getfield        r/k/b/u/y.zp:Lr/k/b/u/y$p;
        //   665: aload           20
        //   667: invokevirtual   r/k/b/u/y$p.d:(Lr/k/b/u/y$p;)Lr/k/b/u/y$u;
        //   670: astore          23
        //   672: new             Lr/k/b/u/y$l;
        //   675: dup            
        //   676: aload           23
        //   678: getfield        r/k/b/u/y$u.swv:D
        //   681: aload           23
        //   683: getfield        r/k/b/u/y$u.swj:D
        //   686: iload           14
        //   688: ifeq            695
        //   691: getstatic       r/k/b/u/y$l$s.sex:Lr/k/b/u/y$l$s;
        //   694: nop            
        //   695: getstatic       r/k/b/u/y$l$s.sek:Lr/k/b/u/y$l$s;
        //   698: invokespecial   r/k/b/u/y$l.<init>:(DDLr/k/b/u/y$l$s;)V
        //   701: areturn        
        //   702: aload           7
        //   704: getstatic       r/k/b/u/y$i.syy:Lr/k/b/u/y$i;
        //   707: if_acmpne       1061
        //   710: iload           14
        //   712: ifeq            1061
        //   715: dload_1        
        //   716: d2f            
        //   717: dload_3        
        //   718: d2f            
        //   719: dload           5
        //   721: d2f            
        //   722: aload_0        
        //   723: getfield        r/k/b/u/y.uh:Ljava/nio/FloatBuffer;
        //   726: aload_0        
        //   727: getfield        r/k/b/u/y.zd:Ljava/nio/FloatBuffer;
        //   730: aload_0        
        //   731: getfield        r/k/b/u/y.um:Ljava/nio/IntBuffer;
        //   734: aload_0        
        //   735: getfield        r/k/b/u/y.ua:Ljava/nio/FloatBuffer;
        //   738: invokestatic    org/lwjgl/util/glu/GLU.gluProject:(FFFLjava/nio/FloatBuffer;Ljava/nio/FloatBuffer;Ljava/nio/IntBuffer;Ljava/nio/FloatBuffer;)Z
        //   741: ifeq            912
        //   744: aload_0        
        //   745: getfield        r/k/b/u/y.ua:Ljava/nio/FloatBuffer;
        //   748: iconst_0       
        //   749: invokevirtual   java/nio/FloatBuffer.get:(I)F
        //   752: f2d            
        //   753: aload_0        
        //   754: getfield        r/k/b/u/y.zv:D
        //   757: dmul           
        //   758: dstore          15
        //   760: aload_0        
        //   761: getfield        r/k/b/u/y.zq:D
        //   764: aload_0        
        //   765: getfield        r/k/b/u/y.ua:Ljava/nio/FloatBuffer;
        //   768: iconst_1       
        //   769: invokevirtual   java/nio/FloatBuffer.get:(I)F
        //   772: f2d            
        //   773: dsub           
        //   774: aload_0        
        //   775: getfield        r/k/b/u/y.zj:D
        //   778: dmul           
        //   779: dstore          17
        //   781: iload           12
        //   783: ifeq            814
        //   786: aload_0        
        //   787: getfield        r/k/b/u/y.zk:D
        //   790: aload_0        
        //   791: getfield        r/k/b/u/y.zv:D
        //   794: dmul           
        //   795: dload           15
        //   797: dsub           
        //   798: dstore          15
        //   800: aload_0        
        //   801: getfield        r/k/b/u/y.zq:D
        //   804: aload_0        
        //   805: getfield        r/k/b/u/y.zj:D
        //   808: dmul           
        //   809: dload           17
        //   811: dsub           
        //   812: dstore          17
        //   814: dload           15
        //   816: dconst_0       
        //   817: dcmpg          
        //   818: ifge            825
        //   821: dconst_0       
        //   822: dstore          15
        //   824: nop            
        //   825: dload           15
        //   827: aload_0        
        //   828: getfield        r/k/b/u/y.zk:D
        //   831: aload_0        
        //   832: getfield        r/k/b/u/y.zv:D
        //   835: dmul           
        //   836: dcmpl          
        //   837: ifle            851
        //   840: aload_0        
        //   841: getfield        r/k/b/u/y.zk:D
        //   844: aload_0        
        //   845: getfield        r/k/b/u/y.zv:D
        //   848: dmul           
        //   849: dstore          15
        //   851: dload           17
        //   853: dconst_0       
        //   854: dcmpg          
        //   855: ifge            862
        //   858: dconst_0       
        //   859: dstore          17
        //   861: nop            
        //   862: dload           17
        //   864: aload_0        
        //   865: getfield        r/k/b/u/y.zq:D
        //   868: aload_0        
        //   869: getfield        r/k/b/u/y.zj:D
        //   872: dmul           
        //   873: dcmpl          
        //   874: ifle            888
        //   877: aload_0        
        //   878: getfield        r/k/b/u/y.zq:D
        //   881: aload_0        
        //   882: getfield        r/k/b/u/y.zj:D
        //   885: dmul           
        //   886: dstore          17
        //   888: new             Lr/k/b/u/y$l;
        //   891: dup            
        //   892: dload           15
        //   894: dload           17
        //   896: iload           14
        //   898: ifeq            905
        //   901: getstatic       r/k/b/u/y$l$s.sex:Lr/k/b/u/y$l$s;
        //   904: nop            
        //   905: getstatic       r/k/b/u/y$l$s.sek:Lr/k/b/u/y$l$s;
        //   908: invokespecial   r/k/b/u/y$l.<init>:(DDLr/k/b/u/y$l$s;)V
        //   911: areturn        
        //   912: new             Lr/k/b/u/y$l;
        //   915: dup            
        //   916: dconst_0       
        //   917: dconst_0       
        //   918: getstatic       r/k/b/u/y$l$s.seq:Lr/k/b/u/y$l$s;
        //   921: invokespecial   r/k/b/u/y$l.<init>:(DDLr/k/b/u/y$l$s;)V
        //   924: areturn        
        //   925: dload_1        
        //   926: d2f            
        //   927: dload_3        
        //   928: d2f            
        //   929: dload           5
        //   931: d2f            
        //   932: aload_0        
        //   933: getfield        r/k/b/u/y.uh:Ljava/nio/FloatBuffer;
        //   936: aload_0        
        //   937: getfield        r/k/b/u/y.zd:Ljava/nio/FloatBuffer;
        //   940: aload_0        
        //   941: getfield        r/k/b/u/y.um:Ljava/nio/IntBuffer;
        //   944: aload_0        
        //   945: getfield        r/k/b/u/y.ua:Ljava/nio/FloatBuffer;
        //   948: invokestatic    org/lwjgl/util/glu/GLU.gluProject:(FFFLjava/nio/FloatBuffer;Ljava/nio/FloatBuffer;Ljava/nio/IntBuffer;Ljava/nio/FloatBuffer;)Z
        //   951: ifeq            1048
        //   954: aload_0        
        //   955: getfield        r/k/b/u/y.ua:Ljava/nio/FloatBuffer;
        //   958: iconst_0       
        //   959: invokevirtual   java/nio/FloatBuffer.get:(I)F
        //   962: f2d            
        //   963: aload_0        
        //   964: getfield        r/k/b/u/y.zv:D
        //   967: dmul           
        //   968: dstore          15
        //   970: aload_0        
        //   971: getfield        r/k/b/u/y.zq:D
        //   974: aload_0        
        //   975: getfield        r/k/b/u/y.ua:Ljava/nio/FloatBuffer;
        //   978: iconst_1       
        //   979: invokevirtual   java/nio/FloatBuffer.get:(I)F
        //   982: f2d            
        //   983: dsub           
        //   984: aload_0        
        //   985: getfield        r/k/b/u/y.zj:D
        //   988: dmul           
        //   989: dstore          17
        //   991: iload           12
        //   993: ifeq            1024
        //   996: aload_0        
        //   997: getfield        r/k/b/u/y.zk:D
        //  1000: aload_0        
        //  1001: getfield        r/k/b/u/y.zv:D
        //  1004: dmul           
        //  1005: dload           15
        //  1007: dsub           
        //  1008: dstore          15
        //  1010: aload_0        
        //  1011: getfield        r/k/b/u/y.zq:D
        //  1014: aload_0        
        //  1015: getfield        r/k/b/u/y.zj:D
        //  1018: dmul           
        //  1019: dload           17
        //  1021: dsub           
        //  1022: dstore          17
        //  1024: new             Lr/k/b/u/y$l;
        //  1027: dup            
        //  1028: dload           15
        //  1030: dload           17
        //  1032: iload           14
        //  1034: ifeq            1041
        //  1037: getstatic       r/k/b/u/y$l$s.sex:Lr/k/b/u/y$l$s;
        //  1040: nop            
        //  1041: getstatic       r/k/b/u/y$l$s.sek:Lr/k/b/u/y$l$s;
        //  1044: invokespecial   r/k/b/u/y$l.<init>:(DDLr/k/b/u/y$l$s;)V
        //  1047: areturn        
        //  1048: new             Lr/k/b/u/y$l;
        //  1051: dup            
        //  1052: dconst_0       
        //  1053: dconst_0       
        //  1054: getstatic       r/k/b/u/y$l$s.seq:Lr/k/b/u/y$l$s;
        //  1057: invokespecial   r/k/b/u/y$l.<init>:(DDLr/k/b/u/y$l$s;)V
        //  1060: areturn        
        //  1061: nop            
        //  1062: dload_1        
        //  1063: d2f            
        //  1064: dload_3        
        //  1065: d2f            
        //  1066: dload           5
        //  1068: d2f            
        //  1069: aload_0        
        //  1070: getfield        r/k/b/u/y.uh:Ljava/nio/FloatBuffer;
        //  1073: aload_0        
        //  1074: getfield        r/k/b/u/y.zd:Ljava/nio/FloatBuffer;
        //  1077: aload_0        
        //  1078: getfield        r/k/b/u/y.um:Ljava/nio/IntBuffer;
        //  1081: aload_0        
        //  1082: getfield        r/k/b/u/y.ua:Ljava/nio/FloatBuffer;
        //  1085: invokestatic    org/lwjgl/util/glu/GLU.gluProject:(FFFLjava/nio/FloatBuffer;Ljava/nio/FloatBuffer;Ljava/nio/IntBuffer;Ljava/nio/FloatBuffer;)Z
        //  1088: ifeq            1143
        //  1091: aload_0        
        //  1092: getfield        r/k/b/u/y.ua:Ljava/nio/FloatBuffer;
        //  1095: iconst_0       
        //  1096: invokevirtual   java/nio/FloatBuffer.get:(I)F
        //  1099: f2d            
        //  1100: aload_0        
        //  1101: getfield        r/k/b/u/y.zv:D
        //  1104: dmul           
        //  1105: dstore          12
        //  1107: aload_0        
        //  1108: getfield        r/k/b/u/y.zq:D
        //  1111: aload_0        
        //  1112: getfield        r/k/b/u/y.ua:Ljava/nio/FloatBuffer;
        //  1115: iconst_1       
        //  1116: invokevirtual   java/nio/FloatBuffer.get:(I)F
        //  1119: f2d            
        //  1120: dsub           
        //  1121: aload_0        
        //  1122: getfield        r/k/b/u/y.zj:D
        //  1125: dmul           
        //  1126: dstore          14
        //  1128: new             Lr/k/b/u/y$l;
        //  1131: dup            
        //  1132: dload           12
        //  1134: dload           14
        //  1136: getstatic       r/k/b/u/y$l$s.sey:Lr/k/b/u/y$l$s;
        //  1139: invokespecial   r/k/b/u/y$l.<init>:(DDLr/k/b/u/y$l$s;)V
        //  1142: areturn        
        //  1143: new             Lr/k/b/u/y$l;
        //  1146: dup            
        //  1147: dconst_0       
        //  1148: dconst_0       
        //  1149: getstatic       r/k/b/u/y$l$s.seq:Lr/k/b/u/y$l$s;
        //  1152: invokespecial   r/k/b/u/y$l.<init>:(DDLr/k/b/u/y$l$s;)V
        //  1155: areturn        
        //  1156: new             Lr/k/b/u/y$l;
        //  1159: dup            
        //  1160: dconst_0       
        //  1161: dconst_0       
        //  1162: getstatic       r/k/b/u/y$l$s.seq:Lr/k/b/u/y$l$s;
        //  1165: invokespecial   r/k/b/u/y$l.<init>:(DDLr/k/b/u/y$l$s;)V
        //  1168: areturn        
        //    StackMapTable: 00 2B FF 00 04 00 18 07 00 09 03 03 03 07 00 12 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 01 FF 00 01 00 18 07 00 09 03 03 03 07 00 12 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 4F 00 18 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 01 40 01 FF 00 1D 00 18 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 00 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 40 01 FF 00 2F 00 18 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 01 40 01 FF 00 0B 00 18 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 01 00 00 00 00 00 00 00 00 00 01 01 01 00 00 0C 09 0C FF 00 7D 00 16 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 01 03 03 00 00 00 00 00 01 01 01 00 00 FB 00 41 0C FF 00 7E 00 15 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 01 03 03 07 00 0C 07 00 0F 03 00 01 01 01 00 00 FF 00 2B 00 15 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 01 03 03 07 00 0C 07 00 0F 03 07 00 0C 01 01 01 00 00 1F 1F 0A FF 00 16 00 15 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 01 03 03 07 00 0C 07 00 0F 03 07 00 0C 01 01 01 00 04 08 02 A0 08 02 A0 03 03 FF 00 02 00 15 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 01 03 03 07 00 0C 07 00 0F 03 07 00 0C 01 01 01 00 05 08 02 A0 08 02 A0 03 03 07 00 15 FF 00 03 00 18 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 01 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FF 00 6F 00 16 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 01 03 03 00 00 00 00 00 01 01 01 00 00 0A 19 0A 19 FF 00 10 00 16 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 01 03 03 00 00 00 00 00 01 01 01 00 04 08 03 78 08 03 78 03 03 FF 00 02 00 16 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 01 03 03 00 00 00 00 00 01 01 01 00 05 08 03 78 08 03 78 03 03 07 00 15 FF 00 03 00 18 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 01 00 00 00 00 00 00 00 00 00 01 01 01 00 00 0C FF 00 62 00 16 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 01 03 03 00 00 00 00 00 01 01 01 00 00 FF 00 10 00 16 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 01 03 03 00 00 00 00 00 01 01 01 00 04 08 04 00 08 04 00 03 03 FF 00 02 00 16 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 01 03 03 00 00 00 00 00 01 01 01 00 05 08 04 00 08 04 00 03 03 07 00 15 FF 00 03 00 18 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 01 07 00 F1 01 00 00 00 00 00 00 00 00 00 01 01 01 00 00 0C FF 00 00 00 18 07 00 09 03 03 03 07 00 12 01 07 00 0C 07 00 F1 01 00 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 FB 00 50 FF 00 0C 00 18 07 00 09 03 03 03 07 00 12 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean[] d(final y$u[] array, final y$u y$u, final double n, final double n2, final double n3) {
        final y$u y$u2 = new y$u(n, n2, n3);
        return new boolean[] { this.d(new y$u[] { y$u, array[3], array[0] }, y$u2), this.d(new y$u[] { y$u, array[0], array[1] }, y$u2), this.d(new y$u[] { y$u, array[1], array[2] }, y$u2), this.d(new y$u[] { y$u, array[2], array[3] }, y$u2) };
    }
    
    public boolean d(final y$u[] p0, final y$u p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: goto            6
        //     4: iconst_0       
        //     5: ireturn        
        //     6: nop            
        //     7: nop            
        //     8: new             Lr/k/b/u/y$u;
        //    11: dup            
        //    12: dconst_0       
        //    13: dconst_0       
        //    14: dconst_0       
        //    15: invokespecial   r/k/b/u/y$u.<init>:(DDD)V
        //    18: astore_3       
        //    19: aload_1        
        //    20: iconst_1       
        //    21: aaload         
        //    22: aload_1        
        //    23: iconst_0       
        //    24: aaload         
        //    25: invokevirtual   r/k/b/u/y$u.s:(Lr/k/b/u/y$u;)Lr/k/b/u/y$u;
        //    28: astore          4
        //    30: aload           4
        //    32: aload           5
        //    34: invokevirtual   r/k/b/u/y$u.y:(Lr/k/b/u/y$u;)Lr/k/b/u/y$u;
        //    37: invokevirtual   r/k/b/u/y$u.xo:()Lr/k/b/u/y$u;
        //    40: astore          6
        //    42: aload_3        
        //    43: aload           6
        //    45: invokevirtual   r/k/b/u/y$u.s:(Lr/k/b/u/y$u;)Lr/k/b/u/y$u;
        //    48: aload_1        
        //    49: iconst_2       
        //    50: aaload         
        //    51: invokevirtual   r/k/b/u/y$u.l:(Lr/k/b/u/y$u;)D
        //    54: dstore          7
        //    56: aload           6
        //    58: aload_2        
        //    59: invokevirtual   r/k/b/u/y$u.l:(Lr/k/b/u/y$u;)D
        //    62: dload           7
        //    64: dadd           
        //    65: dstore          9
        //    67: dload           9
        //    69: dconst_0       
        //    70: dcmpl          
        //    71: iflt            76
        //    74: iconst_1       
        //    75: nop            
        //    76: iconst_0       
        //    77: ireturn        
        //    StackMapTable: 00 05 FF 00 04 00 0E 07 00 09 07 01 22 07 00 0C 00 00 00 00 00 00 00 00 00 00 01 00 00 01 FF 00 17 00 0E 07 00 09 07 01 22 07 00 0C 07 00 0C 07 00 0C 07 00 0C 00 00 00 00 00 01 01 01 00 00 FF 00 2D 00 0C 07 00 09 07 01 22 07 00 0C 07 00 0C 07 00 0C 07 00 0C 07 00 0C 03 03 01 01 01 00 00 40 01
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public y$u[] d(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8) {
        this.d(n4, n5).xo();
        final double n9 = 2.0 * Math.tan(Math.toRadians(n6 / 2.0)) * n7;
        final double n10 = n9 * n8;
        final y$u xo = this.d(n4, n5).xo();
        final y$u xo2 = this.d(n4, n5 - 90.0).xo();
        final y$u xo3 = this.d(n4 + 90.0, 0.0).xo();
        final y$u y$u;
        final y$u d = xo.d(y$u);
        final y$u y$u2 = new y$u(d.swv * n7, d.swj * n7, d.swe * n7);
        return new y$u[] { new y$u(y$u2.swv + xo2.swv * n9 / 2.0 - xo3.swv * n10 / 2.0, y$u2.swj + xo2.swj * n9 / 2.0 - xo3.swj * n10 / 2.0, y$u2.swe + xo2.swe * n9 / 2.0 - xo3.swe * n10 / 2.0), new y$u(y$u2.swv - xo2.swv * n9 / 2.0 - xo3.swv * n10 / 2.0, y$u2.swj - xo2.swj * n9 / 2.0 - xo3.swj * n10 / 2.0, y$u2.swe - xo2.swe * n9 / 2.0 - xo3.swe * n10 / 2.0), new y$u(y$u2.swv - xo2.swv * n9 / 2.0 + xo3.swv * n10 / 2.0, y$u2.swj - xo2.swj * n9 / 2.0 + xo3.swj * n10 / 2.0, y$u2.swe - xo2.swe * n9 / 2.0 + xo3.swe * n10 / 2.0), new y$u(y$u2.swv + xo2.swv * n9 / 2.0 + xo3.swv * n10 / 2.0, y$u2.swj + xo2.swj * n9 / 2.0 + xo3.swj * n10 / 2.0, y$u2.swe + xo2.swe * n9 / 2.0 + xo3.swe * n10 / 2.0) };
    }
    
    public y$u[] ll() {
        return this.zl;
    }
    
    public float ly() {
        return this.zw;
    }
    
    public float lx() {
        return this.zb;
    }
    
    public y$u lk() {
        return this.zg;
    }
    
    public y$u d(final double n, final double n2) {
        final double cos = Math.cos(-n * 0.01745329238474369 - 3.141592653589793);
        final double sin = Math.sin(-n * 0.01745329238474369 - 3.141592653589793);
        final double n3 = -Math.cos(-n2 * 0.01745329238474369);
        return new y$u(sin * n3, Math.sin(-n2 * 0.01745329238474369), cos * n3);
    }
}
