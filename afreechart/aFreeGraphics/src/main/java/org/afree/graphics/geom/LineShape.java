/*   1:    */ package org.afree.graphics.geom;
/*   2:    */ 
/*   3:    */ import android.graphics.Canvas;
/*   4:    */ import android.graphics.Paint;
/*   5:    */ import android.graphics.Paint.Style;
/*   6:    */ import android.graphics.Path;
/*   7:    */ import android.graphics.PointF;
/*   8:    */ import android.graphics.Rect;
/*   9:    */ 
/*  10:    */ public class LineShape
/*  11:    */   implements Shape
/*  12:    */ {
/*  13:    */   private float mX1;
/*  14:    */   private float mY1;
/*  15:    */   private float mX2;
/*  16:    */   private float mY2;
/*  17:    */   
/*  18:    */   public LineShape()
/*  19:    */   {
/*  20: 37 */     this(0.0F, 0.0F, 0.0F, 0.0F);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public LineShape(PointF p1, PointF p2)
/*  24:    */   {
/*  25: 46 */     this(p1.x, p1.y, p2.x, p2.y);
/*  26:    */   }
/*  27:    */   
/*  28:    */   public LineShape(float x1, float y1, float x2, float y2)
/*  29:    */   {
/*  30: 57 */     this.mX1 = x1;
/*  31: 58 */     this.mY1 = y1;
/*  32: 59 */     this.mX2 = x2;
/*  33: 60 */     this.mY2 = y2;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public LineShape(double x1, double y1, double x2, double y2)
/*  37:    */   {
/*  38: 71 */     this.mX1 = ((float)x1);
/*  39: 72 */     this.mY1 = ((float)y1);
/*  40: 73 */     this.mX2 = ((float)x2);
/*  41: 74 */     this.mY2 = ((float)y2);
/*  42:    */   }
/*  43:    */   
/*  44:    */   public LineShape(LineShape lineShape)
/*  45:    */   {
/*  46: 82 */     this(lineShape.getX1(), lineShape.getY1(), lineShape.getX2(), lineShape.getY2());
/*  47:    */   }
/*  48:    */   
/*  49:    */   public Path getPath()
/*  50:    */   {
/*  51: 89 */     Path path = new Path();
/*  52: 90 */     path.moveTo(this.mX1, this.mY1);
/*  53: 91 */     path.lineTo(this.mX2, this.mY2);
/*  54: 92 */     return path;
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void clip(Canvas canvas) {}
/*  58:    */   
/*  59:    */   public void draw(Canvas canvas, Paint paint)
/*  60:    */   {
/*  61:106 */     paint.setStyle(Paint.Style.STROKE);
/*  62:107 */     canvas.drawLine(this.mX1, this.mY1, this.mX2, this.mY2, paint);
/*  63:    */   }
/*  64:    */   
/*  65:    */   public void fill(Canvas canvas, Paint paint)
/*  66:    */   {
/*  67:114 */     paint.setStyle(Paint.Style.FILL);
/*  68:115 */     canvas.drawLine(this.mX1, this.mY1, this.mX2, this.mY2, paint);
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void fillAndStroke(Canvas canvas, Paint paint)
/*  72:    */   {
/*  73:122 */     paint.setStyle(Paint.Style.FILL_AND_STROKE);
/*  74:123 */     canvas.drawLine(this.mX1, this.mY1, this.mX2, this.mY2, paint);
/*  75:    */   }
/*  76:    */   
/*  77:    */   public boolean contains(float x, float y)
/*  78:    */   {
/*  79:131 */     return false;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public boolean contains(float x, float y, float width, float height)
/*  83:    */   {
/*  84:139 */     return false;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public boolean contains(PointF point)
/*  88:    */   {
/*  89:147 */     return false;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public boolean contains(RectShape rect)
/*  93:    */   {
/*  94:155 */     return false;
/*  95:    */   }
/*  96:    */   
/*  97:    */   @Deprecated
/*  98:    */   public RectShape getBounds()
/*  99:    */   {
/* 100:163 */     return new RectShape(this.mX1, this.mY1, this.mX2 - this.mX1, this.mY2 - this.mY1);
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void getBounds(RectShape rect)
/* 104:    */   {
/* 105:170 */     rect.setRect(this.mX1, this.mY1, this.mX2 - this.mX1, this.mY2 - this.mY1);
/* 106:    */   }
/* 107:    */   
/* 108:    */   public boolean intersects(float x, float y, float width, float height)
/* 109:    */   {
/* 110:177 */     return intersects(new RectShape(x, y, width, height));
/* 111:    */   }
/* 112:    */   
/* 113:    */   public boolean intersects(Rect rect)
/* 114:    */   {
/* 115:184 */     return intersects(rect.left, rect.top, rect.width(), rect.height());
/* 116:    */   }
/* 117:    */   
/* 118:    */   public boolean intersects(RectShape rect)
/* 119:    */   {
/* 120:191 */     return rect.intersectsLine(getX1(), getY1(), getX2(), getY2());
/* 121:    */   }
/* 122:    */   
/* 123:    */   public static boolean linesIntersect(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4)
/* 124:    */   {
/* 125:208 */     boolean flag1 = isCCW(x1, y1, x2, y2, x3, y3) ^ isCCW(x1, y1, x2, y2, x4, y4);
/* 126:209 */     boolean flag2 = isCCW(x3, y3, x4, y4, x1, y1) ^ isCCW(x3, y3, x4, y4, x2, y2);
/* 127:210 */     return (flag1) && (flag2);
/* 128:    */   }
/* 129:    */   
/* 130:    */   public boolean intersectsLine(float x1, float y1, float x2, float y2)
/* 131:    */   {
/* 132:222 */     return linesIntersect(x1, y1, x2, y2, getX1(), getY1(), getX2(), 
/* 133:223 */       getY2());
/* 134:    */   }
/* 135:    */   
/* 136:    */   private static boolean isCCW(float x1, float y1, float x2, float y2, float x3, float y3)
/* 137:    */   {
/* 138:237 */     if (getSignedTriangleArea(x1, y1, x2, y2, x3, y3) > 0.0F) {
/* 139:238 */       return true;
/* 140:    */     }
/* 141:240 */     return false;
/* 142:    */   }
/* 143:    */   
/* 144:    */   private static float getSignedTriangleArea(float x1, float y1, float x2, float y2, float x3, float y3)
/* 145:    */   {
/* 146:256 */     x1 -= x3;
/* 147:257 */     y1 -= y3;
/* 148:258 */     x2 -= x3;
/* 149:259 */     y2 -= y3;
/* 150:    */     
/* 151:261 */     return (x1 * y2 - y1 * x2) * 0.5F;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public void translate(float x, float y)
/* 155:    */   {
/* 156:268 */     this.mX1 += x;
/* 157:269 */     this.mY1 += y;
/* 158:270 */     this.mX2 += x;
/* 159:271 */     this.mY2 += y;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public void setLine(float x1, float y1, float x2, float y2)
/* 163:    */   {
/* 164:282 */     this.mX1 = x1;
/* 165:283 */     this.mY1 = y1;
/* 166:284 */     this.mX2 = x2;
/* 167:285 */     this.mY2 = y2;
/* 168:    */   }
/* 169:    */   
/* 170:    */   public void setLine(double x1, double y1, double x2, double y2)
/* 171:    */   {
/* 172:296 */     this.mX1 = ((float)x1);
/* 173:297 */     this.mY1 = ((float)y1);
/* 174:298 */     this.mX2 = ((float)x2);
/* 175:299 */     this.mY2 = ((float)y2);
/* 176:    */   }
/* 177:    */   
/* 178:    */   public void setLine(PointF p1, PointF p2)
/* 179:    */   {
/* 180:308 */     setLine(p1.x, p1.y, p2.x, p2.y);
/* 181:    */   }
/* 182:    */   
/* 183:    */   public float getX1()
/* 184:    */   {
/* 185:316 */     return this.mX1;
/* 186:    */   }
/* 187:    */   
/* 188:    */   public float getY1()
/* 189:    */   {
/* 190:324 */     return this.mY1;
/* 191:    */   }
/* 192:    */   
/* 193:    */   public float getX2()
/* 194:    */   {
/* 195:332 */     return this.mX2;
/* 196:    */   }
/* 197:    */   
/* 198:    */   public float getY2()
/* 199:    */   {
/* 200:340 */     return this.mY2;
/* 201:    */   }
/* 202:    */   
/* 203:    */   public PointF getP1()
/* 204:    */   {
/* 205:348 */     return new PointF(this.mX1, this.mY1);
/* 206:    */   }
/* 207:    */   
/* 208:    */   public void setP1(PointF p)
/* 209:    */   {
/* 210:356 */     this.mX1 = p.x;
/* 211:357 */     this.mY1 = p.y;
/* 212:    */   }
/* 213:    */   
/* 214:    */   public PointF getP2()
/* 215:    */   {
/* 216:365 */     return new PointF(this.mX2, this.mY2);
/* 217:    */   }
/* 218:    */   
/* 219:    */   public void setP2(PointF p)
/* 220:    */   {
/* 221:373 */     this.mX2 = p.x;
/* 222:374 */     this.mY2 = p.y;
/* 223:    */   }
/* 224:    */   
/* 225:    */   public Shape clone()
/* 226:    */   {
/* 227:382 */     return new LineShape(this);
/* 228:    */   }
/* 229:    */   
/* 230:    */   public boolean equals(Object o)
/* 231:    */   {
/* 232:390 */     if ((o instanceof LineShape))
/* 233:    */     {
/* 234:391 */       LineShape lineShape = (LineShape)o;
/* 235:392 */       if ((this.mX1 == lineShape.mX1) && 
/* 236:393 */         (this.mY1 == lineShape.mY1) && 
/* 237:394 */         (this.mX2 == lineShape.mX2) && 
/* 238:395 */         (this.mY2 == lineShape.mY2)) {
/* 239:397 */         return true;
/* 240:    */       }
/* 241:    */     }
/* 242:400 */     return false;
/* 243:    */   }
/* 244:    */ }


/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar
 * Qualified Name:     org.afree.graphics.geom.LineShape
 * JD-Core Version:    0.7.0.1
 */