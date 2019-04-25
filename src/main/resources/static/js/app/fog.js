!function (e) {
    var t = {};

    function n(i) {
        if (t[i]) return t[i].exports;

        var o = t[i] = {i: i, l: !1, exports: {}};

        return e[i].call(o.exports, o, o.exports, n), o.l = !0, o.exports
    }

    n.m = e, n.c = t, n.d = function (e, t, i) {
        n.o(e, t) || Object.defineProperty(e, t, {enumerable: !0, get: i})
    }, n.r = function (e) {
        "undefined" != typeof Symbol && Symbol.toStringTag && Object.defineProperty(e, Symbol.toStringTag, {value: "Module"}), Object.defineProperty(e, "__esModule", {value: !0})
    }, n.t = function (e, t) {
        if (1 & t && (e = n(e)), 8 & t) return e;

        if (4 & t && "object" == typeof e && e && e.__esModule) return e;

        var i = Object.create(null);

        if (n.r(i), Object.defineProperty(i, "default", {
            enumerable: !0,
            value: e
        }), 2 & t && "string" != typeof e) for (var o in e) n.d(i, o, function (t) {
            return e[t]
        }.bind(null, o));

        return i
    }, n.n = function (e) {
        var t = e && e.__esModule ? function () {
            return e.default
        } : function () {
            return e
        };

        return n.d(t, "a", t), t
    }, n.o = function (e, t) {
        return Object.prototype.hasOwnProperty.call(e, t)
    }, n.p = "", n(n.s = 11)
}([function (e, t, n) {
    "use strict";

    function i(e, t) {
        for (var n in t) t.hasOwnProperty(n) && (e[n] = t[n]);

        return e
    }

    function o() {
        return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) || window.innerWidth < 600
    }

    n.d(t, "c", function () {
        return i
    }), n.d(t, "d", function () {
        return o
    }), n.d(t, "h", function () {
        return s
    }), n.d(t, "g", function () {
        return r
    }), n.d(t, "f", function () {
        return a
    }), n.d(t, "e", function () {
        return h
    }), n.d(t, "a", function () {
        return u
    }), n.d(t, "b", function () {
        return l
    }), Number.prototype.clamp = function (e, t) {
        return Math.min(Math.max(this, e), t)
    };

    const s = e => e[Math.floor(Math.random() * e.length)];

    function r(e, t) {
        return null == e && (e = 0), null == t && (t = 1), e + Math.random() * (t - e)
    }

    function a(e, t) {
        return null == e && (e = 0), null == t && (t = 1), Math.floor(e + Math.random() * (t - e + 1))
    }

    var h = e => document.querySelector(e);

    const u = e => "number" == typeof e ? "#" + ("00000" + e.toString(16)).slice(-6) : e, l = (e, t = 1) => {
        const n = u(e), i = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(n),
            o = i ? {r: parseInt(i[1], 16), g: parseInt(i[2], 16), b: parseInt(i[3], 16)} : null;

        return "rgba(" + o.r + "," + o.g + "," + o.b + "," + t + ")"
    }
}, function (e, t, n) {
    "use strict";
    n.d(t, "a", function () {
        return o
    });

    var i = n(0);

    window && !window.VANTA && (window.VANTA = {version: "0.3.1"});

    const o = window.VANTA || {};
    o.register || (o.register = ((e, t) => {
        o[e] = (e => new t(e))
    }));

    var s = function () {
        return Array.prototype.unshift.call(arguments, "[VANTA]"), console.error.apply(this, arguments)
    };

    o.VantaBase = class {
        constructor(e = {}) {
            var t, n, r, a;

            if (o.current = this, this.onMouseMoveWrapper = this.onMouseMoveWrapper.bind(this), this.resize = this.resize.bind(this), this.animationLoop = this.animationLoop.bind(this), this.restart = this.restart.bind(this), this.options = Object(i.c)({}, this.defaultOptions), e instanceof HTMLElement || "string" == typeof e ? Object(i.c)(this.options, {el: e}) : Object(i.c)(this.options, e), this.el = this.options.el, null == this.el) s('Instance needs "el" param!');

            else if (!(this.options.el instanceof HTMLElement || (a = this.el, this.el = Object(i.e)(a), this.el))) return void s("Cannot find element", a);

            for (r = 0; r < this.el.children.length; r++) t = this.el.children[r], "static" === getComputedStyle(t).position && (t.style.position = "relative"), "auto" === getComputedStyle(t).zIndex && (t.style.zIndex = 1);

            "static" === getComputedStyle(this.el).position && (this.el.style.position = "relative"), "object" == typeof THREE && this.initThree(), this.setSize(), this.uniforms = {
                u_time: {
                    type: "f",
                    value: 1
                },
                u_resolution: {type: "v2", value: new THREE.Vector2(1, 1)},
                u_mouse: {type: "v2", value: new THREE.Vector2(0, 0)}
            };

            try {
                this.init()
            } catch (e) {
                return n = e, s("Init error"), s(n), this.el.removeChild(this.renderer.domElement), void (this.options.backgroundColor && (console.log("[VANTA] Falling back to backgroundColor"), this.el.style.background = this.color2Hex(this.options.backgroundColor)))
            }
            window.addEventListener("resize", this.resize), this.resize(), this.animationLoop(), this.el.addEventListener("mousemove", this.onMouseMoveWrapper, !1), window.addEventListener("scroll", this.onMouseMoveWrapper)
        }

        applyCanvasStyles(e, t = {}) {
            Object(i.c)(e.style, {
                position: "absolute",
                zIndex: 0,
                top: 0,
                left: 0,
                background: ""
            }), Object(i.c)(e.style, t), e.classList.add("vanta-canvas")
        }

        initThree() {
            this.renderer = new THREE.WebGLRenderer({
                alpha: !0,
                antialias: !0
            }), this.el.appendChild(this.renderer.domElement), this.applyCanvasStyles(this.renderer.domElement), isNaN(this.options.backgroundAlpha) && (this.options.backgroundAlpha = 1), this.scene = new THREE.Scene
        }

        onMouseMoveWrapper(e) {
            var t, n, i;

            t = this.renderer.domElement.getBoundingClientRect(), n = this.mouseX = e.clientX - t.left, i = this.mouseY = e.clientY - t.top, n >= 0 && i >= 0 && !this.options.mouseEase && this.triggerMouseMove(n, i)
        }

        triggerMouseMove(e, t) {
            this.uniforms && (this.uniforms.u_mouse.value.x = e / this.scale, this.uniforms.u_mouse.value.y = t / this.scale);

            const n = e / this.width, i = t / this.height;

            "function" == typeof this.onMouseMove && this.onMouseMove(n, i)
        }

        setSize() {
            this.scale || (this.scale = 1), Object(i.d)() && this.options.scaleMobile ? this.scale = this.options.scaleMobile : this.options.scale && (this.scale = this.options.scale), this.width = this.el.offsetWidth || window.innerWidth, this.height = this.el.offsetHeight || window.innerHeight
        }

        resize() {

            var e, t;

            this.setSize(), null != (e = this.camera) && (e.aspect = this.width / this.height), null != (t = this.camera) && "function" == typeof t.updateProjectionMatrix && t.updateProjectionMatrix(), this.renderer && (this.renderer.setSize(this.width, this.height), this.renderer.setPixelRatio(window.devicePixelRatio / this.scale)), "function" == typeof this.onResize && this.onResize()
        }

        animationLoop() {

            var e, t, n, i, o, s, r, a;

            return this.t || (this.t = 0), this.t += 1, this.t2 || (this.t2 = 0), this.t2 += null != (s = this.options.speed) ? s : 1, this.uniforms && (this.uniforms.u_time.value = .016667 * this.t2), e = this.el.offsetHeight, t = this.el.getBoundingClientRect(), a = null != (r = window.pageYOffset) ? r : (document.documentElement || document.body.parentNode || document.body).scrollTop, i = (o = t.top + a) - window.innerHeight, n = o + e, this.options.mouseEase && (this.mouseEaseX = this.mouseEaseX || this.mouseX || 0, this.mouseEaseY = this.mouseEaseY || this.mouseY || 0, Math.abs(this.mouseEaseX - this.mouseX) + Math.abs(this.mouseEaseY - this.mouseY) > .1 && (this.mouseEaseX = this.mouseEaseX + .05 * (this.mouseX - this.mouseEaseX), this.mouseEaseY = this.mouseEaseY + .05 * (this.mouseY - this.mouseEaseY), this.triggerMouseMove(this.mouseEaseX, this.mouseEaseY))), i <= a && a <= n && ("function" == typeof this.onUpdate && this.onUpdate(), this.scene && this.camera && (this.renderer.render(this.scene, this.camera), "transparent" == this.options.backgroundColor ? this.renderer.setClearColor(this.options.backgroundColor, 0) : this.renderer.setClearColor(this.options.backgroundColor, this.options.backgroundAlpha)), this.fps && this.fps.update && this.fps.update()), this.req = window.requestAnimationFrame(this.animationLoop)
        }

        restart() {
            if (this.scene) for (; this.scene.children.length;) this.scene.remove(this.scene.children[0]);
            "function" == typeof this.onRestart && this.onRestart(), this.init()
        }

        init() {
            "function" == typeof this.onInit && this.onInit()
        }

        destroy() {
            "function" == typeof this.onDestroy && this.onDestroy(), this.el.removeEventListener("mousemove", this.onMouseMoveWrapper), window.removeEventListener("scroll", this.onMouseMoveWrapper), window.removeEventListener("resize", this.resize), window.cancelAnimationFrame(this.req), this.renderer && (this.el.removeChild(this.renderer.domElement), this.renderer = null, this.scene = null)
        }
    }, t.b = o.VantaBase
}, function (e, t, n) {
    "use strict";
    n.d(t, "b", function () {
        return s
    });
    var i = n(1), o = n(0);
    n.d(t, "a", function () {
        return i.a
    }), "object" == typeof THREE && (THREE.Color.prototype.toVector = function () {
        return new THREE.Vector3(this.r, this.g, this.b)
    });

    class s extends i.b {
        constructor(e) {
            super(e), this.mode = "shader", this.updateUniforms = this.updateUniforms.bind(this)
        }

        initBasicShader(e = this.fragmentShader, t = this.vertexShader) {
            var n, i, o;
            return t || (t = "uniform float u_time;\nuniform vec2 u_resolution;\nvoid main() {\n  gl_Position = vec4( position, 1.0 );\n}"), this.updateUniforms(), "function" == typeof this.valuesChanger && this.valuesChanger(), n = new THREE.ShaderMaterial({
                uniforms: this.uniforms,
                vertexShader: t,
                fragmentShader: e
            }), (o = this.options.texturePath) && (this.uniforms.u_tex = {
                type: "t",
                value: (new THREE.TextureLoader).load(o)
            }), i = new THREE.Mesh(new THREE.PlaneGeometry(2, 2), n), this.scene.add(i), this.camera = new THREE.Camera, this.camera.position.z = 1
        }

        updateUniforms() {
            var e, t, n, i;
            for (e in t = {}, n = this.options) i = n[e], -1 !== e.toLowerCase().indexOf("color") ? t[e] = {
                type: "v3",
                value: new THREE.Color(i).toVector()
            } : "number" == typeof i && (t[e] = {type: "f", value: i});
            return Object(o.c)(this.uniforms, t)
        }

        init() {
            super.init(), this.fragmentShader && this.initBasicShader()
        }

        resize() {
            super.resize(), this.uniforms.u_resolution.value.x = this.width / this.scale, this.uniforms.u_resolution.value.y = this.height / this.scale
        }
    }
}, , , , , , , , , function (e, t, n) {
    "use strict";
    n.r(t);
    var i = n(2);

    class o extends i.b {
    }

    i.a.register("FOG", o), o.prototype.defaultOptions = {
        highlightColor: 0xffd1,
        midtoneColor: 0x0,
        lowlightColor: 0x18a28a,
        baseColor: 0x0,
        blurFactor: .6,
        speed: 1,
        zoom: 1,
        scale: 2,
        scaleMobile: 4
    }, o.prototype.fragmentShader = "uniform vec2 u_resolution;\nuniform vec2 u_mouse;\nuniform float u_time;\n\nuniform float blurFactor;\nuniform vec3 baseColor;\nuniform vec3 lowlightColor;\nuniform vec3 midtoneColor;\nuniform vec3 highlightColor;\nuniform float zoom;\n\nfloat random (in vec2 _st) {\n  return fract(sin(dot(_st.xy,\n                        vec2(12.9898,78.233)))*\n      43758.5453123);\n}\n\n// Based on Morgan McGuire @morgan3d\n// https://www.shadertoy.com/view/4dS3Wd\nfloat noise (in vec2 _st) {\n  vec2 i = floor(_st);\n  vec2 f = fract(_st);\n\n  // Four corners in 2D of a tile\n  float a = random(i);\n  float b = random(i + vec2(1.0, 0.0));\n  float c = random(i + vec2(0.0, 1.0));\n  float d = random(i + vec2(1.0, 1.0));\n\n  vec2 u = f * f * (3.0 - 2.0 * f);\n\n  return mix(a, b, u.x) +\n          (c - a)* u.y * (1.0 - u.x) +\n          (d - b) * u.x * u.y;\n}\n\n#define NUM_OCTAVES 6\n\nfloat fbm ( in vec2 _st) {\n  float v = 0.0;\n  float a = blurFactor;\n  vec2 shift = vec2(100.0);\n  // Rotate to reduce axial bias\n  mat2 rot = mat2(cos(0.5), sin(0.5),\n                  -sin(0.5), cos(0.50));\n  for (int i = 0; i < NUM_OCTAVES; ++i) {\n      v += a * noise(_st);\n      _st = rot * _st * 2.0 + shift;\n      a *= (1. - blurFactor);\n  }\n  return v;\n}\n\nvoid main() {\n  vec2 st = gl_FragCoord.xy / u_resolution.xy*3.;\n  st.x *= 0.7 * u_resolution.x / u_resolution.y ; // Still keep it more landscape than square\n  st *= zoom;\n\n  // st += st * abs(sin(u_time*0.1)*3.0);\n  vec3 color = vec3(0.0);\n\n  vec2 q = vec2(0.);\n  q.x = fbm( st + 0.00*u_time);\n  q.y = fbm( st + vec2(1.0));\n\n  vec2 dir = vec2(0.15,0.126);\n  vec2 r = vec2(0.);\n  r.x = fbm( st + 1.0*q + vec2(1.7,9.2)+ dir.x*u_time );\n  r.y = fbm( st + 1.0*q + vec2(8.3,2.8)+ dir.y*u_time);\n\n  float f = fbm(st+r);\n\n  color = mix(baseColor,\n              lowlightColor,\n              clamp((f*f)*4.0,0.0,1.0));\n\n  color = mix(color,\n              midtoneColor,\n              clamp(length(q),0.0,1.0));\n\n  color = mix(color,\n              highlightColor,\n              clamp(length(r.x),0.0,1.0));\n\n  vec3 finalColor = mix(baseColor, color, f*f*f+.6*f*f+.5*f);\n  gl_FragColor = vec4(finalColor,1.0);\n}\n"
}]);