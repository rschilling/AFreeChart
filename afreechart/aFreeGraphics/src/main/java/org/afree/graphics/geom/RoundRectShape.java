/*   1:    */ package org.afree.graphics.geom;
/*   2:    */ 
/*   3:    */ import android.graphics.Canvas;
/*   4:    */ import android.graphics.Paint;
/*   5:    */ import android.graphics.Paint.Style;
/*   6:    */ import android.graphics.Path;
/*   7:    */ import android.graphics.Path.Direction;
/*   8:    */ import android.graphics.PointF;
/*   9:    */ import android.graphics.Rect;
/*  10:    */ import android.graphics.RectF;
/*  11:    */ import android.graphics.Region;
/*  12:    */ import android.graphics.Region.Op;
/*  13:    */ 
/*  14:    */ public class RoundRectShape
/*  15:    */   implements Shape
/*  16:    */ {
/*  17:    */   private RectF mRect;
/*  18:    */   private float mRX;
/*  19:    */   private float mRY;
/*  20:    */   private Path mPath;
/*  21:    */   private Region mRegion;
/*  22: 42 */   private RectF rectBuffer = new RectF();
/*  23:    */   
/*  24:    */   public RoundRectShape()
/*  25:    */   {
/*  26: 48 */     this(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/*  27:    */   }
/*  28:    */   
/*  29:    */   public RoundRectShape(float x, float y, float width, float height, float rx, float ry)
/*  30:    */   {
/*  31: 61 */     this.mRect = new RectF(x, y, x + width, y + height);
/*  32: 62 */     this.mRX = rx;
/*  33: 63 */     this.mRY = ry;
/*  34: 64 */     this.mPath = new Path();
/*  35: 65 */     update();
/*  36:    */   }
/*  37:    */   
/*  38:    */   public RoundRectShape(RoundRectShape rect)
/*  39:    */   {
/*  40: 73 */     this(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), rect.getRx(), rect.getRy());
/*  41:    */     
/*  42: 75 */     this.mPath = new Path(rect.mPath);
/*  43: 76 */     this.mRegion = new Region(rect.mRegion);
/*  44:    */   }
/*  45:    */   
/*  46:    */   public Path getPath()
/*  47:    */   {
/*  48: 83 */     return this.mPath;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void draw(Canvas canvas, Paint paint)
/*  52:    */   {
/*  53: 90 */     paint.setStyle(Paint.Style.STROKE);
/*  54: 91 */     canvas.drawPath(this.mPath, paint);
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void fill(Canvas canvas, Paint paint)
/*  58:    */   {
/*  59: 98 */     paint.setStyle(Paint.Style.FILL);
/*  60: 99 */     canvas.drawPath(this.mPath, paint);
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void fillAndStroke(Canvas canvas, Paint paint)
/*  64:    */   {
/*  65:106 */     paint.setStyle(Paint.Style.FILL_AND_STROKE);
/*  66:107 */     canvas.drawPath(this.mPath, paint);
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void clip(Canvas canvas)
/*  70:    */   {
/*  71:114 */     canvas.clipPath(this.mPath);
/*  72:    */   }
/*  73:    */   
/*  74:    */   public boolean contains(RectShape rect)
/*  75:    */   {
/*  76:121 */     return contains(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
/*  77:    */   }
/*  78:    */   
/*  79:    */   public boolean contains(float x, float y)
/*  80:    */   {
/*  81:128 */     return this.mRegion.contains((int)x, (int)y);
/*  82:    */   }
/*  83:    */   
/*  84:    */   public boolean contains(float x, float y, float width, float height)
/*  85:    */   {
/*  86:135 */     if ((this.mRegion.contains((int)x, (int)y)) && 
/*  87:136 */       (this.mRegion.contains((int)x, (int)(y + height))) && 
/*  88:137 */       (this.mRegion.contains((int)(x + width), (int)y)) && 
/*  89:138 */       (this.mRegion.contains((int)(x + width), (int)(y + height)))) {
/*  90:140 */       return true;
/*  91:    */     }
/*  92:142 */     return false;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public boolean contains(PointF point)
/*  96:    */   {
/*  97:149 */     return this.mRegion.contains((int)point.x, (int)point.y);
/*  98:    */   }
/*  99:    */   
/* 100:    */   @Deprecated
/* 101:    */   public RectShape getBounds()
/* 102:    */   {
/* 103:157 */     RectF rect = new RectF();
/* 104:158 */     this.mPath.computeBounds(rect, false);
/* 105:159 */     return new RectShape(rect);
/* 106:    */   }
/* 107:    */   
/* 108:    */   public void getBounds(RectShape rect)
/* 109:    */   {
/* 110:166 */     this.mPath.computeBounds(this.rectBuffer, false);
/* 111:167 */     rect.setRectF(this.rectBuffer);
/* 112:    */   }
/* 113:    */   
/* 114:    */   public boolean intersects(float x, float y, float width, float height)
/* 115:    */   {
/* 116:174 */     Region region = new Region(this.mRegion);
/* 117:    */     float tmpw;
/* 118:    */     float tmpx;
/* 119:    */     
/* 120:177 */     if (x < width + x)
/* 121:    */     {
/* 122:178 */       tmpx = x;
/* 123:179 */       tmpw = width;
/* 124:    */     }
/* 125:    */     else
/* 126:    */     {
/* 127:182 */       tmpx = width + x;
/* 128:183 */       tmpw = Math.abs(width);
/* 129:    */     }
/* 130:    */     float tmph;
/* 131:    */     float tmpy;
/* 132:    */   
/* 133:185 */     if (y < height + y)
/* 134:    */     {
/* 135:186 */       tmpy = y;
/* 136:187 */       tmph = height;
/* 137:    */     }
/* 138:    */     else
/* 139:    */     {
/* 140:190 */       tmpy = height + y;
/* 141:191 */       tmph = Math.abs(height);
/* 142:    */     }
/* 143:193 */     return region.op((int)tmpx, (int)tmpy, (int)(tmpx + tmpw), (int)(tmpy + tmph), Region.Op.INTERSECT);
/* 144:    */   }
/* 145:    */   
/* 146:    */   public boolean intersects(Rect rect)
/* 147:    */   {
/* 148:200 */     return intersects(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
/* 149:    */   }
/* 150:    */   
/* 151:    */   public boolean intersects(RectShape rect)
/* 152:    */   {
/* 153:207 */     return intersects(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
/* 154:    */   }
/* 155:    */   
/* 156:    */   public void translate(float x, float y)
/* 157:    */   {
/* 158:214 */     this.mRect.left += x;
/* 159:215 */     this.mRect.top += y;
/* 160:216 */     this.mRect.right += x;
/* 161:217 */     this.mRect.bottom += y;
/* 162:    */   }
/* 163:    */   
/* 164:    */   public float getX()
/* 165:    */   {
/* 166:225 */     return this.mRect.left;
/* 167:    */   }
/* 168:    */   
/* 169:    */   public void setX(float x)
/* 170:    */   {
/* 171:233 */     this.mRect.left = x;
/* 172:    */   }
/* 173:    */   
/* 174:    */   public float getY()
/* 175:    */   {
/* 176:241 */     return this.mRect.top;
/* 177:    */   }
/* 178:    */   
/* 179:    */   public void setY(float y)
/* 180:    */   {
/* 181:249 */     this.mRect.top = y;
/* 182:    */   }
/* 183:    */   
/* 184:    */   public float getWidth()
/* 185:    */   {
/* 186:257 */     return this.mRect.width();
/* 187:    */   }
/* 188:    */   
/* 189:    */   public void setWidth(float width)
/* 190:    */   {
/* 191:265 */     this.mRect.right = (getMinX() + width);
/* 192:    */   }
/* 193:    */   
/* 194:    */   public float getHeight()
/* 195:    */   {
/* 196:273 */     return this.mRect.height();
/* 197:    */   }
/* 198:    */   
/* 199:    */   public void setHeight(float height)
/* 200:    */   {
/* 201:281 */     this.mRect.bottom = (getMinY() + height);
/* 202:    */   }
/* 203:    */   
/* 204:    */   public float getRx()
/* 205:    */   {
/* 206:288 */     return this.mRX;
/* 207:    */   }
/* 208:    */   
/* 209:    */   public void setRx(float rx)
/* 210:    */   {
/* 211:297 */     this.mRX = rx;
/* 212:    */   }
/* 213:    */   
/* 214:    */   public float getRy()
/* 215:    */   {
/* 216:305 */     return this.mRY;
/* 217:    */   }
/* 218:    */   
/* 219:    */   public void setRy(float ry)
/* 220:    */   {
/* 221:313 */     this.mRY = ry;
/* 222:    */   }
/* 223:    */   
/* 224:    */   public float getMinX()
/* 225:    */   {
/* 226:321 */     return getX();
/* 227:    */   }
/* 228:    */   
/* 229:    */   public float getMaxX()
/* 230:    */   {
/* 231:329 */     return this.mRect.right;
/* 232:    */   }
/* 233:    */   
/* 234:    */   public float getCenterX()
/* 235:    */   {
/* 236:337 */     return this.mRect.centerX();
/* 237:    */   }
/* 238:    */   
/* 239:    */   public float getMinY()
/* 240:    */   {
/* 241:345 */     return getY();
/* 242:    */   }
/* 243:    */   
/* 244:    */   public float getMaxY()
/* 245:    */   {
/* 246:353 */     return this.mRect.bottom;
/* 247:    */   }
/* 248:    */   
/* 249:    */   public float getCenterY()
/* 250:    */   {
/* 251:361 */     return this.mRect.centerY();
/* 252:    */   }
/* 253:    */   
/* 254:    */   private void update()
/* 255:    */   {
/* 256:368 */     this.mPath.reset();
/* 257:369 */     this.mPath.addRoundRect(this.mRect, this.mRX, this.mRY, Path.Direction.CW);
/* 258:370 */     this.mRegion = new Region((int)getMinX(), (int)getMinY(), (int)getMaxX(), (int)getMaxY());
/* 259:371 */     this.mRegion.setPath(this.mPath, this.mRegion);
/* 260:    */   }
/* 261:    */   
/* 262:    */   public boolean equals(Object o)
/* 263:    */   {
/* 264:379 */     if ((o instanceof RoundRectShape))
/* 265:    */     {
/* 266:380 */       RoundRectShape roundRectShape = (RoundRectShape)o;
/* 267:381 */       if ((getX() == roundRectShape.getX()) && 
/* 268:382 */         (getY() == roundRectShape.getY()) && 
/* 269:383 */         (getWidth() == roundRectShape.getWidth()) && 
/* 270:384 */         (getHeight() == roundRectShape.getHeight()) && 
/* 271:385 */         (this.mRX == roundRectShape.mRX) && 
/* 272:386 */         (this.mRY == roundRectShape.mRY)) {
/* 273:388 */         return true;
/* 274:    */       }
/* 275:    */     }
/* 276:391 */     return false;
/* 277:    */   }
/* 278:    */   
/* 279:    */   public RoundRectShape clone()
/* 280:    */   {
/* 281:399 */     return new RoundRectShape(this);
/* 282:    */   }
/* 283:    */ }


/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar
 * Qualified Name:     org.afree.graphics.geom.RoundRectShape
 * JD-Core Version:    0.7.0.1
 */