/*   1:    */ package org.afree.graphics.geom;
/*   2:    */ 
/*   3:    */ import android.graphics.Canvas;
/*   4:    */ import android.graphics.Paint;
/*   5:    */ import android.graphics.Paint.Style;
/*   6:    */ import android.graphics.Path;
/*   7:    */ import android.graphics.PointF;
/*   8:    */ import android.graphics.Rect;
/*   9:    */ import android.graphics.RectF;
/*  10:    */ import android.graphics.Region;
/*  11:    */ import android.graphics.Region.Op;
/*  12:    */ 
/*  13:    */ public class CubicCurveShape
/*  14:    */   implements Shape
/*  15:    */ {
/*  16:    */   private float mX1;
/*  17:    */   private float mY1;
/*  18:    */   private float mX2;
/*  19:    */   private float mY2;
/*  20:    */   private float mCtrlx1;
/*  21:    */   private float mCtrly1;
/*  22:    */   private float mCtrlx2;
/*  23:    */   private float mCtrly2;
/*  24:    */   private Path mPath;
/*  25:    */   private Region mRegion;
/*  26: 37 */   private Region regionBuffer = new Region();
/*  27:    */   
/*  28:    */   public CubicCurveShape()
/*  29:    */   {
/*  30: 43 */     this(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/*  31:    */   }
/*  32:    */   
/*  33:    */   public CubicCurveShape(float x1, float y1, float ctrlx1, float ctrly1, float ctrlx2, float ctrly2, float x2, float y2)
/*  34:    */   {
/*  35: 59 */     this.mX1 = x1;
/*  36: 60 */     this.mY1 = y1;
/*  37: 61 */     this.mCtrlx1 = ctrlx1;
/*  38: 62 */     this.mCtrly1 = ctrly1;
/*  39: 63 */     this.mCtrlx2 = ctrlx2;
/*  40: 64 */     this.mCtrly2 = ctrly2;
/*  41: 65 */     this.mX2 = x2;
/*  42: 66 */     this.mY2 = y2;
/*  43: 67 */     this.mPath = new Path();
/*  44: 68 */     this.mRegion = new Region();
/*  45:    */     
/*  46: 70 */     update();
/*  47:    */   }
/*  48:    */   
/*  49:    */   public CubicCurveShape(CubicCurveShape curve)
/*  50:    */   {
/*  51: 78 */     this.mX1 = curve.mX1;
/*  52: 79 */     this.mY1 = curve.mY1;
/*  53: 80 */     this.mCtrlx1 = curve.mCtrlx1;
/*  54: 81 */     this.mCtrly1 = curve.mCtrly1;
/*  55: 82 */     this.mCtrlx2 = curve.mCtrlx2;
/*  56: 83 */     this.mCtrly2 = curve.mCtrly2;
/*  57: 84 */     this.mX2 = curve.mX2;
/*  58: 85 */     this.mY2 = curve.mY2;
/*  59: 86 */     this.mPath = curve.mPath;
/*  60: 87 */     this.mRegion = curve.mRegion;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public Path getPath()
/*  64:    */   {
/*  65: 94 */     return this.mPath;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public void draw(Canvas canvas, Paint paint)
/*  69:    */   {
/*  70:101 */     paint.setStyle(Paint.Style.STROKE);
/*  71:102 */     canvas.drawPath(this.mPath, paint);
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void fill(Canvas canvas, Paint paint)
/*  75:    */   {
/*  76:109 */     paint.setStyle(Paint.Style.FILL);
/*  77:110 */     canvas.drawPath(this.mPath, paint);
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void fillAndStroke(Canvas canvas, Paint paint)
/*  81:    */   {
/*  82:117 */     paint.setStyle(Paint.Style.FILL_AND_STROKE);
/*  83:118 */     canvas.drawPath(this.mPath, paint);
/*  84:    */   }
/*  85:    */   
/*  86:    */   public void clip(Canvas canvas) {}
/*  87:    */   
/*  88:    */   public boolean contains(float x, float y)
/*  89:    */   {
/*  90:134 */     return false;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public boolean contains(float x, float y, float width, float height)
/*  94:    */   {
/*  95:142 */     return false;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public boolean contains(PointF point)
/*  99:    */   {
/* 100:150 */     return false;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public boolean contains(RectShape rect)
/* 104:    */   {
/* 105:158 */     return false;
/* 106:    */   }
/* 107:    */   
/* 108:    */   @Deprecated
/* 109:    */   public RectShape getBounds()
/* 110:    */   {
/* 111:166 */     return new RectShape(this.regionBuffer.getBounds());
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void getBounds(RectShape rect)
/* 115:    */   {
/* 116:173 */     rect.setRect(this.regionBuffer.getBounds());
/* 117:    */   }
/* 118:    */   
/* 119:    */   public boolean intersects(float x, float y, float width, float height)
/* 120:    */   {
/* 121:181 */     Region region = new Region(this.mRegion);
/* 122:    */     float tmpw;
/* 123:    */     float tmpx;
/* 124:    */     
/* 125:184 */     if (x < width + x)
/* 126:    */     {
/* 127:185 */       tmpx = x;
/* 128:186 */       tmpw = width;
/* 129:    */     }
/* 130:    */     else
/* 131:    */     {
/* 132:189 */       tmpx = width + x;
/* 133:190 */       tmpw = Math.abs(width);
/* 134:    */     }
/* 135:    */     float tmph;
/* 136:    */     float tmpy;
/* 137:    */     
/* 138:192 */     if (y < height + y)
/* 139:    */     {
/* 140:193 */       tmpy = y;
/* 141:194 */       tmph = height;
/* 142:    */     }
/* 143:    */     else
/* 144:    */     {
/* 145:197 */       tmpy = height + y;
/* 146:198 */       tmph = Math.abs(height);
/* 147:    */     }
/* 148:200 */     return region.op((int)tmpx, (int)tmpy, (int)(tmpx + tmpw), (int)(tmpy + tmph), Region.Op.INTERSECT);
/* 149:    */   }
/* 150:    */   
/* 151:    */   public boolean intersects(Rect rect)
/* 152:    */   {
/* 153:207 */     return intersects(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
/* 154:    */   }
/* 155:    */   
/* 156:    */   public boolean intersects(RectShape rect)
/* 157:    */   {
/* 158:214 */     return intersects(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
/* 159:    */   }
/* 160:    */   
/* 161:    */   public void translate(float x, float y)
/* 162:    */   {
/* 163:221 */     this.mX1 += x;
/* 164:222 */     this.mY1 += y;
/* 165:223 */     this.mCtrlx1 += x;
/* 166:224 */     this.mCtrly1 += y;
/* 167:225 */     this.mCtrlx2 += x;
/* 168:226 */     this.mCtrly2 += y;
/* 169:227 */     this.mX2 += x;
/* 170:228 */     this.mY2 += y;
/* 171:    */     
/* 172:230 */     update();
/* 173:    */   }
/* 174:    */   
/* 175:    */   public void setCurve(float x1, float y1, float ctrlx1, float ctrly1, float ctrlx2, float ctrly2, float x2, float y2)
/* 176:    */   {
/* 177:246 */     this.mX1 = x1;
/* 178:247 */     this.mY1 = y1;
/* 179:248 */     this.mCtrlx1 = ctrlx1;
/* 180:249 */     this.mCtrly1 = ctrly1;
/* 181:250 */     this.mCtrlx2 = ctrlx2;
/* 182:251 */     this.mCtrly2 = ctrly2;
/* 183:252 */     this.mX2 = x2;
/* 184:253 */     this.mY2 = y2;
/* 185:    */     
/* 186:255 */     update();
/* 187:    */   }
/* 188:    */   
/* 189:    */   public void setCurve(double x1, double y1, double ctrlx1, double ctrly1, double ctrlx2, double ctrly2, double x2, double y2)
/* 190:    */   {
/* 191:271 */     this.mX1 = ((float)x1);
/* 192:272 */     this.mY1 = ((float)y1);
/* 193:273 */     this.mCtrlx1 = ((float)ctrlx1);
/* 194:274 */     this.mCtrly1 = ((float)ctrly1);
/* 195:275 */     this.mCtrlx2 = ((float)ctrlx2);
/* 196:276 */     this.mCtrly2 = ((float)ctrly2);
/* 197:277 */     this.mX2 = ((float)x2);
/* 198:278 */     this.mY2 = ((float)y2);
/* 199:    */     
/* 200:280 */     update();
/* 201:    */   }
/* 202:    */   
/* 203:    */   private void update()
/* 204:    */   {
/* 205:287 */     this.mPath.reset();
/* 206:288 */     this.mPath.moveTo(this.mX1, this.mY1);
/* 207:289 */     this.mPath.cubicTo(this.mCtrlx1, this.mCtrly1, this.mCtrlx2, this.mCtrly2, this.mX2, this.mY2);
/* 208:    */     
/* 209:291 */     RectF rect = new RectF();
/* 210:292 */     this.mPath.computeBounds(rect, false);
/* 211:293 */     this.mRegion = new Region((int)rect.left, (int)rect.top, (int)rect.right, (int)rect.bottom);
/* 212:294 */     this.regionBuffer.set(this.mRegion);
/* 213:    */     
/* 214:296 */     this.mPath.lineTo(this.mX2 + 1.0F, this.mY2);
/* 215:297 */     this.mPath.cubicTo(this.mCtrlx2 + 1.0F, this.mCtrly2, this.mCtrlx1 + 1.0F, this.mCtrly1, this.mX1 + 1.0F, this.mY1);
/* 216:298 */     this.mPath.close();
/* 217:    */     
/* 218:300 */     this.mRegion.setPath(this.mPath, this.mRegion);
/* 219:    */   }
/* 220:    */   
/* 221:    */   public boolean equals(Object o)
/* 222:    */   {
/* 223:308 */     if ((o instanceof CubicCurveShape))
/* 224:    */     {
/* 225:309 */       CubicCurveShape cubicCurveShape = (CubicCurveShape)o;
/* 226:310 */       if ((this.mX1 == cubicCurveShape.mX1) && 
/* 227:311 */         (this.mY1 == cubicCurveShape.mY1) && 
/* 228:312 */         (this.mX2 == cubicCurveShape.mX2) && 
/* 229:313 */         (this.mY2 == cubicCurveShape.mY2) && 
/* 230:314 */         (this.mCtrlx1 == cubicCurveShape.mCtrlx1) && 
/* 231:315 */         (this.mCtrly1 == cubicCurveShape.mCtrly1) && 
/* 232:316 */         (this.mCtrlx2 == cubicCurveShape.mCtrlx2) && 
/* 233:317 */         (this.mCtrly2 == cubicCurveShape.mCtrly2)) {
/* 234:319 */         return true;
/* 235:    */       }
/* 236:    */     }
/* 237:322 */     return false;
/* 238:    */   }
/* 239:    */   
/* 240:    */   public Shape clone()
/* 241:    */   {
/* 242:330 */     return new CubicCurveShape(this);
/* 243:    */   }
/* 244:    */ }


/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar
 * Qualified Name:     org.afree.graphics.geom.CubicCurveShape
 * JD-Core Version:    0.7.0.1
 */