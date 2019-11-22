#!/bin/sh

echo "leJOS NXJ> Compiling files..."
nxjc Core.java || exit 1
echo "leJOS NXJ> successfuly compiled"
nxj -r -o Core.nxj Core || exit 1

if [ -f "Core.nxj" ]
then
	rm Core.nxj
fi
echo "leJOS NXJ> Cleaning successful"
rm *.class