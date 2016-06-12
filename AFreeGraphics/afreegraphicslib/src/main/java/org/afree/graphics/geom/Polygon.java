/*   1:    */ package org.afree.graphics.geom;
/*   2:    */ 
/*   3:    */ import android.graphics.Canvas;
/*   4:    */ import android.graphics.Matrix;
/*   5:    */ import android.graphics.Paint;
/*   6:    */ import android.graphics.Paint.Style;
/*   7:    */ import android.graphics.Path;
/*   8:    */ import android.graphics.PointF;
/*   9:    */ import android.graphics.Rect;
/*  10:    */ import android.graphics.RectF;
/*  11:    */ import android.graphics.Region;
/*  12:    */ import android.graphics.Region.Op;
/*  13:    */ import android.graphics.RegionIterator;
/*  14:    */ 
/*  15:    */ public class Polygon
/*  16:    */   implements Shape
/*  17:    */ {
/*  18:    */   private Path mPath;
/*  19:    */   private Region mRegion;
/*  20: 38 */   private Path mClosedTempPath = new Path();
/*  21: 39 */   private boolean mFirstPoint = true;
/*  22:    */   
/*  23:    */   public Polygon()
/*  24:    */   {
/*  25: 45 */     this.mPath = new Path();
/*  26: 46 */     this.mRegion = new Region();
/*  27:    */   }
/*  28:    */   
/*  29:    */   public Polygon(Polygon polygon)
/*  30:    */   {
/*  31: 54 */     this.mPath = new Path(polygon.mPath);
/*  32: 55 */     this.mRegion = new Region(polygon.mRegion);
/*  33: 56 */     this.mFirstPoint = polygon.mFirstPoint;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public Polygon(int[] xs, int[] ys, int vertexNum)
/*  37:    */   {
/*  38: 66 */     if ((xs.length != vertexNum) || (ys.length != vertexNum)) {
/*  39: 67 */       throw new IllegalArgumentException("No match array length.");
/*  40:    */     }
/*  41: 69 */     this.mPath = new Path();
/*  42: 70 */     this.mPath.moveTo(xs[0], ys[0]);
/*  43: 71 */     for (int i = 1; i < vertexNum; i++) {
/*  44: 72 */       this.mPath.lineTo(xs[i], ys[i]);
/*  45:    */     }
/*  46: 74 */     RectF rect = new RectF();
/*  47: 75 */     this.mPath.computeBounds(rect, false);
/*  48: 76 */     this.mRegion = new Region();
/*  49:    */     
/*  50: 78 */     this.mFirstPoint = false;
/*  51:    */     
/*  52: 80 */     update();
/*  53:    */   }
/*  54:    */   
/*  55:    */   public Polygon(float[] xs, float[] ys, int vertexNum)
/*  56:    */   {
/*  57: 90 */     if ((xs.length != vertexNum) || (ys.length != vertexNum)) {
/*  58: 91 */       throw new IllegalArgumentException("No match array length.");
/*  59:    */     }
/*  60: 93 */     this.mPath = new Path();
/*  61: 94 */     this.mPath.moveTo(xs[0], ys[0]);
/*  62: 95 */     for (int i = 1; i < vertexNum; i++) {
/*  63: 96 */       this.mPath.lineTo(xs[i], ys[i]);
/*  64:    */     }
/*  65: 98 */     RectF rect = new RectF();
/*  66: 99 */     this.mPath.computeBounds(rect, false);
/*  67:100 */     this.mRegion = new Region();
/*  68:    */     
/*  69:102 */     this.mFirstPoint = false;
/*  70:    */     
/*  71:104 */     update();
/*  72:    */   }
/*  73:    */   
/*  74:    */   public Path getPath()
/*  75:    */   {
/*  76:111 */     return this.mPath;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public void draw(Canvas canvas, Paint paint)
/*  80:    */   {
/*  81:118 */     updateTempPath();
/*  82:    */     
/*  83:120 */     paint.setStyle(Paint.Style.STROKE);
/*  84:121 */     canvas.drawPath(this.mClosedTempPath, paint);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public void fill(Canvas canvas, Paint paint)
/*  88:    */   {
/*  89:128 */     updateTempPath();
/*  90:    */     
/*  91:130 */     paint.setStyle(Paint.Style.FILL);
/*  92:131 */     canvas.drawPath(this.mClosedTempPath, paint);
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void fillAndStroke(Canvas canvas, Paint paint)
/*  96:    */   {
/*  97:138 */     updateTempPath();
/*  98:    */     
/*  99:140 */     paint.setStyle(Paint.Style.FILL_AND_STROKE);
/* 100:141 */     canvas.drawPath(this.mClosedTempPath, paint);
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void clip(Canvas canvas)
/* 104:    */   {
/* 105:148 */     updateTempPath();
/* 106:    */     
/* 107:150 */     canvas.clipPath(this.mClosedTempPath);
/* 108:    */   }
/* 109:    */   
/* 110:    */   public boolean contains(RectShape rect)
/* 111:    */   {
/* 112:157 */     Region tmpRegion = new Region();
/* 113:158 */     Region targetRegion = new Region((int)rect.getX(), (int)rect.getY(), 
/* 114:159 */       (int)(rect.getWidth() + rect.getX()), (int)(rect.getHeight() + rect.getY()));
/* 115:160 */     tmpRegion.op(this.mRegion, targetRegion, Region.Op.INTERSECT);
/* 116:161 */     return !targetRegion.op(tmpRegion, Region.Op.XOR);
/* 117:    */   }
/* 118:    */   
/* 119:    */   public boolean contains(float x, float y)
/* 120:    */   {
/* 121:168 */     return this.mRegion.contains((int)x, (int)y);
/* 122:    */   }
/* 123:    */   
/* 124:    */   public boolean contains(float x, float y, float width, float height)
/* 125:    */   {
/* 126:175 */     Region tmpRegion = new Region();
/* 127:176 */     Region targetRegion = new Region((int)x, (int)y, (int)(width + x), (int)(height + y));
/* 128:177 */     tmpRegion.op(this.mRegion, targetRegion, Region.Op.INTERSECT);
/* 129:178 */     return !targetRegion.op(tmpRegion, Region.Op.XOR);
/* 130:    */   }
/* 131:    */   
/* 132:    */   public boolean contains(PointF point)
/* 133:    */   {
/* 134:185 */     return this.mRegion.contains((int)point.x, (int)point.y);
/* 135:    */   }
/* 136:    */   
/* 137:    */   @Deprecated
/* 138:    */   public RectShape getBounds()
/* 139:    */   {
/* 140:193 */     return new RectShape(this.mRegion.getBounds());
/* 141:    */   }
/* 142:    */   
/* 143:    */   public void getBounds(RectShape rect)
/* 144:    */   {
/* 145:200 */     rect.setRect(this.mRegion.getBounds());
/* 146:    */   }
/* 147:    */   
/* 148:    */   public boolean intersects(float x, float y, float width, float height)
/* 149:    */   {
/* 150:207 */     if (this.mRegion.isEmpty()) {
/* 151:208 */       return false;
/* 152:    */     }
/* 153:210 */     Rect rect = new Rect();
/* 154:211 */     RegionIterator iterator = new RegionIterator(this.mRegion);
/* 155:212 */     Region region = new Region(this.mRegion);
/* 156:215 */     if (iterator.next(rect))
/* 157:    */     {
/* 158:    */       float tmpw;
/* 159:    */       float tmpx;
/* 160:    */      
/* 161:216 */       if (x < width + x)
/* 162:    */       {
/* 163:217 */         tmpx = x;
/* 164:218 */         tmpw = width;
/* 165:    */       }
/* 166:    */       else
/* 167:    */       {
/* 168:221 */         tmpx = width + x;
/* 169:222 */         tmpw = Math.abs(width);
/* 170:    */       }
/* 171:    */       float tmph;
/* 172:    */       float tmpy;
/* 173:    */  
/* 174:224 */       if (y < height + y)
/* 175:    */       {
/* 176:225 */         tmpy = y;
/* 177:226 */         tmph = height;
/* 178:    */       }
/* 179:    */       else
/* 180:    */       {
/* 181:229 */         tmpy = height + y;
/* 182:230 */         tmph = Math.abs(height);
/* 183:    */       }
/* 184:232 */       return region.op((int)tmpx, (int)tmpy, (int)(tmpx + tmpw), (int)(tmpy + tmph), Region.Op.INTERSECT);
/* 185:    */     }
/* 186:234 */     return false;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public boolean intersects(Rect rect)
/* 190:    */   {
/* 191:241 */     return intersects(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
/* 192:    */   }
/* 193:    */   
/* 194:    */   public boolean intersects(RectShape rect)
/* 195:    */   {
/* 196:248 */     return intersects(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
/* 197:    */   }
/* 198:    */   
/* 199:    */   public void translate(float x, float y)
/* 200:    */   {
/* 201:255 */     Matrix mat = new Matrix();
/* 202:256 */     mat.postTranslate(x, y);
/* 203:257 */     this.mPath.transform(mat);
/* 204:258 */     update();
/* 205:    */   }
/* 206:    */   
/* 207:    */   public void addPoint(float x, float y)
/* 208:    */   {
/* 209:267 */     if (this.mFirstPoint)
/* 210:    */     {
/* 211:268 */       this.mPath.moveTo(x, y);
/* 212:269 */       this.mFirstPoint = false;
/* 213:    */     }
/* 214:    */     else
/* 215:    */     {
/* 216:271 */       this.mPath.lineTo(x, y);
/* 217:    */     }
/* 218:273 */     update();
/* 219:    */   }
/* 220:    */   
/* 221:    */   public void reset()
/* 222:    */   {
/* 223:280 */     this.mPath.reset();
/* 224:281 */     update();
/* 225:    */   }
/* 226:    */   
/* 227:    */   public void update()
/* 228:    */   {
/* 229:288 */     updateTempPath();
/* 230:    */     
/* 231:290 */     RectF rect = new RectF();
/* 232:291 */     this.mPath.computeBounds(rect, false);
/* 233:292 */     this.mRegion = new Region((int)rect.left, (int)rect.top, (int)rect.right, (int)rect.bottom);
/* 234:293 */     this.mRegion.setPath(this.mClosedTempPath, this.mRegion);
/* 235:    */   }
/* 236:    */   
/* 237:    */   private void updateTempPath()
/* 238:    */   {
/* 239:297 */     this.mClosedTempPath.reset();
/* 240:298 */     this.mClosedTempPath.set(this.mPath);
/* 241:299 */     this.mClosedTempPath.close();
/* 242:    */   }
/* 243:    */   
/* 244:    */   public Shape clone()
/* 245:    */   {
/* 246:307 */     return new Polygon(this);
/* 247:    */   }
/* 248:    */   
/* 249:    */   public boolean equals(Object o)
/* 250:    */   {
/* 251:315 */     if ((o instanceof Polygon))
/* 252:    */     {
/* 253:316 */       Polygon polygon = (Polygon)o;
/* 254:317 */       Region region = new Region(this.mRegion);
/* 255:318 */       if (!region.op(polygon.mRegion, Region.Op.XOR)) {
/* 256:319 */         return true;
/* 257:    */       }
/* 258:    */     }
/* 259:322 */     return false;
/* 260:    */   }
/* 261:    */ }


/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar
 * Qualified Name:     org.afree.graphics.geom.Polygon
 * JD-Core Version:    0.7.0.1
 */