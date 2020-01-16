#version 130

in vec2 pos;

uniform vec2 scale;
uniform vec2 offset;
uniform vec2 windowSize;
uniform float r;
uniform vec2 o;

out vec2 textCoords;
out vec2 windowAspect;
out float radius;
out vec2 origin;

void main(void)
{
    gl_Position = vec4(offset + (pos *scale), 0 ,1.0);
    textCoords = (pos + 1) / 2;
    windowAspect = windowSize * scale;
    radius = r;
    origin = origin;
}