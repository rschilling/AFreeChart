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
/*  11:    */ 
/*  12:    */ public class RectShape
/*  13:    */   implements Shape
/*  14:    */ {
/*  15:    */   private RectF mRect;
/*  16:    */   
/*  17:    */   public RectShape()
/*  18:    */   {
/*  19: 39 */     this(0.0F, 0.0F, 0.0F, 0.0F);
/*  20:    */   }
/*  21:    */   
/*  22:    */   public RectShape(RectF rect)
/*  23:    */   {
/*  24: 47 */     this(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
/*  25:    */   }
/*  26:    */   
/*  27:    */   public RectShape(Rect rect)
/*  28:    */   {
/*  29: 55 */     this(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
/*  30:    */   }
/*  31:    */   
/*  32:    */   public RectShape(RectShape rect)
/*  33:    */   {
/*  34: 64 */     this.mRect = new RectF(rect.mRect);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public RectShape(float x, float y, float width, float height)
/*  38:    */   {
/*  39: 75 */     this.mRect = new RectF(x, y, x + width, y + height);
/*  40:    */   }
/*  41:    */   
/*  42:    */   public RectShape(double x, double y, double width, double height)
/*  43:    */   {
/*  44: 86 */     this.mRect = new RectF((float)x, (float)y, (float)(x + width), (float)(y + height));
/*  45:    */   }
/*  46:    */   
/*  47:    */   public Path getPath()
/*  48:    */   {
/*  49: 93 */     Path path = new Path();
/*  50: 94 */     path.addRect(this.mRect, Path.Direction.CW);
/*  51: 95 */     return path;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void clip(Canvas canvas)
/*  55:    */   {
/*  56:102 */     canvas.clipRect(this.mRect);
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void draw(Canvas canvas, Paint paint)
/*  60:    */   {
/*  61:109 */     paint.setStyle(Paint.Style.STROKE);
/*  62:110 */     canvas.drawRect(this.mRect, paint);
/*  63:    */   }
/*  64:    */   
/*  65:    */   public void fill(Canvas canvas, Paint paint)
/*  66:    */   {
/*  67:117 */     paint.setStyle(Paint.Style.FILL);
/*  68:118 */     canvas.drawRect(this.mRect, paint);
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void fillAndStroke(Canvas canvas, Paint paint)
/*  72:    */   {
/*  73:125 */     paint.setStyle(Paint.Style.FILL_AND_STROKE);
/*  74:126 */     canvas.drawRect(this.mRect, paint);
/*  75:    */   }
/*  76:    */   
/*  77:    */   public boolean contains(RectShape rect)
/*  78:    */   {
/*  79:133 */     return contains(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
/*  80:    */   }
/*  81:    */   
/*  82:    */   public boolean contains(float x, float y)
/*  83:    */   {
/*  84:140 */     return this.mRect.contains((int)x, (int)y);
/*  85:    */   }
/*  86:    */   
/*  87:    */   public boolean contains(float x, float y, float width, float height)
/*  88:    */   {
/*  89:147 */     return this.mRect.contains((int)x, (int)y, (int)(x + width), (int)(y + height));
/*  90:    */   }
/*  91:    */   
/*  92:    */   public boolean contains(PointF point)
/*  93:    */   {
/*  94:154 */     return this.mRect.contains(point.x, point.y);
/*  95:    */   }
/*  96:    */   
/*  97:    */   @Deprecated
/*  98:    */   public RectShape getBounds()
/*  99:    */   {
/* 100:163 */     return this;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void getBounds(RectShape rect)
/* 104:    */   {
/* 105:171 */     rect.setRect(this);
/* 106:    */   }
/* 107:    */   
/* 108:    */   public boolean intersects(float x, float y, float width, float height)
/* 109:    */   {
/* 110:179 */     return this.mRect.intersects(x, y, x + width, y + height);
/* 111:    */   }
/* 112:    */   
/* 113:    */   public boolean intersects(Rect rect)
/* 114:    */   {
/* 115:187 */     return intersects(rect.left, rect.top, Math.abs(rect.right - rect.left), Math.abs(rect.bottom - rect.top));
/* 116:    */   }
/* 117:    */   
/* 118:    */   public boolean intersects(RectShape rect)
/* 119:    */   {
/* 120:195 */     return intersects(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
/* 121:    */   }
/* 122:    */   
/* 123:    */   public boolean intersectsLine(float x1, float y1, float x2, float y2)
/* 124:    */   {
/* 125:207 */     return intersectsLine(new LineShape(x1, y1, x2, y2));
/* 126:    */   }
/* 127:    */   
/* 128:    */   public boolean intersectsLine(LineShape line)
/* 129:    */   {
/* 130:216 */     if (line.intersectsLine(getMinX(), getMinY(), getMaxX(), getMinY())) {
/* 131:216 */       return true;
/* 132:    */     }
/* 133:217 */     if (line.intersectsLine(getMinX(), getMinY(), getMinX(), getMaxY())) {
/* 134:217 */       return true;
/* 135:    */     }
/* 136:218 */     if (line.intersectsLine(getMaxX(), getMinY(), getMaxX(), getMaxY())) {
/* 137:218 */       return true;
/* 138:    */     }
/* 139:219 */     if (line.intersectsLine(getMinX(), getMaxY(), getMaxX(), getMaxY())) {
/* 140:219 */       return true;
/* 141:    */     }
/* 142:220 */     if ((contains(line.getX1(), line.getY1())) || (contains(line.getX2(), line.getY2()))) {
/* 143:220 */       return true;
/* 144:    */     }
/* 145:222 */     return false;
/* 146:    */   }
/* 147:    */   
/* 148:    */   public RectShape createUnion(RectShape rect)
/* 149:    */   {
/* 150:232 */     float l = getX() < rect.getX() ? getX() : rect.getX();
/* 151:233 */     float t = getY() < rect.getY() ? getY() : rect.getY();
/* 152:234 */     float r = getX() + getWidth() < rect.getX() + rect.getWidth() ? rect.getX() + rect.getWidth() : getX() + getWidth();
/* 153:235 */     float b = getY() + getHeight() < rect.getY() + rect.getHeight() ? rect.getY() + rect.getHeight() : getY() + getHeight();
/* 154:    */     
/* 155:237 */     return new RectShape(l, t, r - l, b - t);
/* 156:    */   }
/* 157:    */   
/* 158:    */   public boolean isEmpty()
/* 159:    */   {
/* 160:245 */     return (getWidth() <= 0.0F) || (getHeight() <= 0.0F);
/* 161:    */   }
/* 162:    */   
/* 163:    */   public void translate(float x, float y)
/* 164:    */   {
/* 165:252 */     this.mRect.left += x;
/* 166:253 */     this.mRect.top += y;
/* 167:254 */     this.mRect.right += x;
/* 168:255 */     this.mRect.bottom += y;
/* 169:    */   }
/* 170:    */   
/* 171:    */   public void setRect(float x, float y, float width, float height)
/* 172:    */   {
/* 173:266 */     this.mRect.left = x;
/* 174:267 */     this.mRect.top = y;
/* 175:268 */     this.mRect.right = (x + width);
/* 176:269 */     this.mRect.bottom = (y + height);
/* 177:    */   }
/* 178:    */   
/* 179:    */   public void setRect(double x, double y, double width, double height)
/* 180:    */   {
/* 181:280 */     this.mRect.left = ((float)x);
/* 182:281 */     this.mRect.top = ((float)y);
/* 183:282 */     this.mRect.right = ((float)(x + width));
/* 184:283 */     this.mRect.bottom = ((float)(y + height));
/* 185:    */   }
/* 186:    */   
/* 187:    */   public void setRect(RectShape rect)
/* 188:    */   {
/* 189:291 */     this.mRect.left = rect.getX();
/* 190:292 */     this.mRect.top = rect.getY();
/* 191:293 */     this.mRect.right = rect.getMaxX();
/* 192:294 */     this.mRect.bottom = rect.getMaxY();
/* 193:    */   }
/* 194:    */   
/* 195:    */   public void setRect(Rect rect)
/* 196:    */   {
/* 197:302 */     this.mRect.set(rect);
/* 198:    */   }
/* 199:    */   
/* 200:    */   public void setRectF(RectF rectF)
/* 201:    */   {
/* 202:310 */     this.mRect.set(rectF);
/* 203:    */   }
/* 204:    */   
/* 205:    */   public RectF getRectF()
/* 206:    */   {
/* 207:318 */     return new RectF(getMinX(), getMinY(), getMaxX(), getMaxY());
/* 208:    */   }
/* 209:    */   
/* 210:    */   public float getX()
/* 211:    */   {
/* 212:326 */     return this.mRect.left;
/* 213:    */   }
/* 214:    */   
/* 215:    */   public void setX(float x)
/* 216:    */   {
/* 217:334 */     this.mRect.left = x;
/* 218:    */   }
/* 219:    */   
/* 220:    */   public float getY()
/* 221:    */   {
/* 222:342 */     return this.mRect.top;
/* 223:    */   }
/* 224:    */   
/* 225:    */   public void setY(float y)
/* 226:    */   {
/* 227:350 */     this.mRect.top = y;
/* 228:    */   }
/* 229:    */   
/* 230:    */   public float getWidth()
/* 231:    */   {
/* 232:358 */     return this.mRect.width();
/* 233:    */   }
/* 234:    */   
/* 235:    */   public void setWidth(float width)
/* 236:    */   {
/* 237:366 */     this.mRect.right = (getMinX() + width);
/* 238:    */   }
/* 239:    */   
/* 240:    */   public float getHeight()
/* 241:    */   {
/* 242:374 */     return this.mRect.height();
/* 243:    */   }
/* 244:    */   
/* 245:    */   public void setHeight(float height)
/* 246:    */   {
/* 247:382 */     this.mRect.bottom = (getMinY() + height);
/* 248:    */   }
/* 249:    */   
/* 250:    */   public float getMinX()
/* 251:    */   {
/* 252:390 */     return this.mRect.left;
/* 253:    */   }
/* 254:    */   
/* 255:    */   public float getMaxX()
/* 256:    */   {
/* 257:398 */     return this.mRect.right;
/* 258:    */   }
/* 259:    */   
/* 260:    */   public float getCenterX()
/* 261:    */   {
/* 262:406 */     return this.mRect.centerX();
/* 263:    */   }
/* 264:    */   
/* 265:    */   public float getMinY()
/* 266:    */   {
/* 267:414 */     return this.mRect.top;
/* 268:    */   }
/* 269:    */   
/* 270:    */   public float getMaxY()
/* 271:    */   {
/* 272:422 */     return this.mRect.bottom;
/* 273:    */   }
/* 274:    */   
/* 275:    */   public float getCenterY()
/* 276:    */   {
/* 277:430 */     return this.mRect.centerY();
/* 278:    */   }
/* 279:    */   
/* 280:    */   public boolean equals(Object o)
/* 281:    */   {
/* 282:438 */     if ((o instanceof RectShape))
/* 283:    */     {
/* 284:439 */       RectShape rectShape = (RectShape)o;
/* 285:440 */       return (getX() == rectShape.getX()) && (getY() == rectShape.getY()) && (getWidth() == rectShape.getWidth()) && (getHeight() == rectShape.getHeight());
/* 286:    */     }
/* 287:443 */     return false;
/* 288:    */   }
/* 289:    */   
/* 290:    */   public RectShape clone()
/* 291:    */   {
/* 292:452 */     return new RectShape(this);
/* 293:    */   }
/* 294:    */ }


/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar
 * Qualified Name:     org.afree.graphics.geom.RectShape
 * JD-Core Version:    0.7.0.1
 */